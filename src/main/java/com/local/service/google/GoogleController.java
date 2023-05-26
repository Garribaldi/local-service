package com.local.service.google;

import com.local.service.google.data.GoogleCaptchaEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class GoogleController {

    @PostMapping("/google-evaluate")
    @ResponseStatus(HttpStatus.OK)
    public void validate(@RequestBody GoogleCaptchaEntity googleCaptchaEntity) {
        System.out.println(googleCaptchaEntity.toString());
    }
}
