package com.local.service.data;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeEntity {

    private String firstName;
    private String lastName;

    @Nullable
    private String email;
    @Nullable
    private String password;
    @Nullable
    private String city;
    @Nullable
    private String country;
}
