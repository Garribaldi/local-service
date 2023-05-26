package com.local.service.google.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventEntity {

    private String expectedAction;
    private Boolean express;
    private Boolean firewallPolicyEvaluation;
    private String hashedAccountId;
    private String[] headers;
    private String ja3;
    private String requestedUri;
    private String siteKey;
    private String token;
    private String userAgent;
    private String userIpAddress;
    private Boolean wafTokenAssessment;
}
