package com.local.service.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NbaTeamEntity {

    private Integer teamId;
    private String abbreviation;
    private String teamName;
    private String simpleName;
    private String location;
    private String date;
}
