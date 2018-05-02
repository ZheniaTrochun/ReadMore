package com.yevhenii.kpi.readmore.model.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String email;
    private String password;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
