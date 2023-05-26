package com.local.service.google.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RiskAnalysisEntity {

    private String[] extendedVerdictReasons;
    private String[] reasons;
    private Number score;
}
