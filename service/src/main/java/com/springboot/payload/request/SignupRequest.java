package com.springboot.payload.request;

import com.springboot.models.EPosition;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EPosition position;

    private Date dob;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> role;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public EPosition getPosition() { return position; }

    public void setPosition(EPosition position) { this.position = position; }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
      return this.role;
    }

    public void setRole(Set<String> role) {
      this.role = role;
    }






}
