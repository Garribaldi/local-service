package com.local.service.google.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TokenPropertiesEntity {

    private String action;
    private String androidPackageName;
    private String createTime;
    private String hostname;
    private String invalidReason;
    private String iosBundleId;
    private Boolean valid;
}
