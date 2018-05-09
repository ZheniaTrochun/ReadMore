package com.yevhenii.kpi.readmore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//import javax.validation.constraints.Email;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    private String email;

    private String role;

    private String hashedPass;

    private String strategy = "password";

    public User() {
    }

    public User(@NotNull String name, String email, String hashedPass, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.hashedPass = hashedPass;
    }

    public User(String name, String email, String role, String hashedPass, String strategy) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.hashedPass = hashedPass;
        this.strategy = strategy;
    }

    public User(String name, String hashedPass) {
        this.name = name;
        this.hashedPass = hashedPass;
    }
}
