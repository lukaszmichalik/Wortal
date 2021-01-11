package com.springboot.payload.response;

import com.springboot.models.EPosition;

import java.sql.Date;

public class UserResponse {
    private Long id;
    private String name;
    private EPosition position;
    private Date dob;
    private String email;
    private String username;

    public UserResponse( Long id, String name, EPosition position, Date dob, String email, String username) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.dob = dob;
        this.email = email;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public EPosition getPosition() {
        return position;
    }

    public void setPosition(EPosition position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
