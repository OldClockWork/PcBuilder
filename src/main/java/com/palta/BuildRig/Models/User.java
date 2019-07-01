package com.palta.BuildRig.Models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {



    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 4, max = 15, message = "Username should only be between 4 - 15 characters long")
    private String username;

    @NotNull
    @Email(message = "Invalid email")
    private String email;

    @NotNull
    @Size(min = 4,  message = "Password should have more than 4 characters")
    private String password;


    public User(){}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() { return id; }
}
