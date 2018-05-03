package com.yevhenii.kpi.readmore.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginDto {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
