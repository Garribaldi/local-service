package com.local.service.google.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoogleCaptchaResponseDto {

    private EventEntity event;
    private String name;
    private RiskAnalysisEntity riskAnalysis;
    private TokenPropertiesEntity tokenProperties;
}
