package com.yevhenii.kpi.readmore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @Email
    private String email;

    private String role;
    private String hashedPass;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Book> todo;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Book> finished;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Book> inProgress;

    public User() {
        todo = new ArrayList<>();
        finished = new ArrayList<>();
        inProgress = new ArrayList<>();
    }

    public User(String name, String email, String hashedPass, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.hashedPass = hashedPass;
        this.todo = new ArrayList<>();
        this.finished = new ArrayList<>();
        this.inProgress = new ArrayList<>();
    }

    public User(String name, @Email String email, String role, String hashedPass, List<Book> todo, List<Book> finished, List<Book> inProgress) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.hashedPass = hashedPass;
        this.todo = todo;
        this.finished = finished;
        this.inProgress = inProgress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHashedPass() {
        return hashedPass;
    }

    public void setHashedPass(String hashedPass) {
        this.hashedPass = hashedPass;
    }

    public List<Book> getTodo() {
        return todo;
    }

    public void setTodo(List<Book> todo) {
        this.todo = todo;
    }

    public List<Book> getFinished() {
        return finished;
    }

    public void setFinished(List<Book> finished) {
        this.finished = finished;
    }

    public List<Book> getInProgress() {
        return inProgress;
    }

    public void setInProgress(List<Book> inProgress) {
        this.inProgress = inProgress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", hashedPass='" + hashedPass + '\'' +
                ", todo=" + todo +
                ", finished=" + finished +
                ", inProgress=" + inProgress +
                '}';
    }
}
