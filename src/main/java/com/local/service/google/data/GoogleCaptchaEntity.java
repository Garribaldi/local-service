package com.local.service.google.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GoogleCaptchaEntity {

    private String token;
    private String siteKey;
    private String projectId;
}
