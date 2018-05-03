package com.yevhenii.kpi.readmore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;

    private String role;
    @NotNull
    private String hashedPass;

    public User() {
    }

    public User(@NotNull String name, @Email @NotNull String email, @NotNull String hashedPass, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.hashedPass = hashedPass;
    }
}
