package com.local.service.google;

import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.rpc.FixedHeaderProvider;
import com.google.cloud.recaptchaenterprise.v1.RecaptchaEnterpriseServiceClient;
import com.google.cloud.recaptchaenterprise.v1.RecaptchaEnterpriseServiceSettings;
import com.google.recaptchaenterprise.v1.Assessment;
import com.google.recaptchaenterprise.v1.CreateAssessmentRequest;
import com.google.recaptchaenterprise.v1.Event;
import com.google.recaptchaenterprise.v1.ProjectName;
import com.google.recaptchaenterprise.v1.RiskAnalysis;
import com.local.service.google.data.GoogleCaptchaEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GoogleController {

    String recaptchaAction = "";

    @Value("${google.apikey}")
    private String apiKey;

    @PostMapping("/google-evaluate")
    @ResponseStatus(HttpStatus.OK)
    public void validate(@RequestBody GoogleCaptchaEntity googleCaptchaEntity) throws Exception {
        System.out.println(googleCaptchaEntity);

        this.createAssessment(googleCaptchaEntity.getProjectId(), googleCaptchaEntity.getSiteKey(), googleCaptchaEntity.getToken(), recaptchaAction);
    }

    /**
     * Create an assessment to analyze the risk of an UI action. Assessment approach is the same for
     * both 'score' and 'checkbox' type recaptcha site keys.
     *
     * @param projectId        : GCloud Project ID
     * @param recaptchaSiteKey : Site key obtained by registering a domain/app to use recaptcha
     *                         services. (score/ checkbox type)
     * @param token            : The token obtained from the client on passing the recaptchaSiteKey.
     * @param recaptchaAction  : Action name corresponding to the token.
     */
    private void createAssessment(String projectId, String recaptchaSiteKey, String token, String recaptchaAction) throws Exception {

        // Set auth method to Google api key
        final RecaptchaEnterpriseServiceSettings settings = RecaptchaEnterpriseServiceSettings.newBuilder()
                .setCredentialsProvider(NoCredentialsProvider.create())
                .setHeaderProvider(FixedHeaderProvider.create("x-goog-api-key", apiKey))
                .build();


        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the `client.close()` method on the client to safely
        // clean up any remaining background resources.
        try (RecaptchaEnterpriseServiceClient client = RecaptchaEnterpriseServiceClient.create(settings)) {

            // Set the properties of the event to be tracked.
            Event event = Event.newBuilder().setSiteKey(recaptchaSiteKey).setToken(token).build();

            // Build the assessment request.
            CreateAssessmentRequest createAssessmentRequest =
                    CreateAssessmentRequest.newBuilder()
                            .setParent(ProjectName.of(projectId).toString())
                            .setAssessment(Assessment.newBuilder().setEvent(event).build())
                            .build();

            Assessment response = client.createAssessment(createAssessmentRequest);

            // Check if the token is valid.
            if (!response.getTokenProperties().getValid()) {
                System.out.println(
                        "The CreateAssessment call failed because the token was: "
                                + response.getTokenProperties().getInvalidReason().name());
                return;
            }

            // Check if the expected action was executed.
            // (If the key is checkbox type and 'action' attribute wasn't set, skip this check.)
            if (!response.getTokenProperties().getAction().equals(recaptchaAction)) {
                System.out.println(
                        "The action attribute in reCAPTCHA tag is: "
                                + response.getTokenProperties().getAction());
                System.out.println(
                        "The action attribute in the reCAPTCHA tag "
                                + "does not match the action("
                                + recaptchaAction
                                + ") you are expecting to score");
                return;
            }

            // Get the reason(s) and the risk score.
            // For more information on interpreting the assessment,
            // see: https://cloud.google.com/recaptcha-enterprise/docs/interpret-assessment
            for (RiskAnalysis.ClassificationReason reason : response.getRiskAnalysis().getReasonsList()) {
                System.out.println(reason);
            }

            float recaptchaScore = response.getRiskAnalysis().getScore();
            System.out.println("The reCAPTCHA score is: " + recaptchaScore);

            // Get the assessment name (id). Use this to annotate the assessment.
            String assessmentName = response.getName();
            System.out.println(
                    "Assessment name: " + assessmentName.substring(assessmentName.lastIndexOf("/") + 1));
        }
    }
}
