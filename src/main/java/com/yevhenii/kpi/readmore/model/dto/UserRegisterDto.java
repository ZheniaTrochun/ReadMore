package com.yevhenii.kpi.readmore.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

@Data
public class UserRegisterDto {
    @NotNull
    private String username;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
