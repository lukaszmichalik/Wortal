package com.springboot.payload.response;

import com.springboot.models.User;

import java.sql.Date;
import java.util.Set;

public class TeamResponse {
    private Long id;
    private String name;
    private String location;
    private Date creationDate;
    private String description;
    private Set<User> players;
    private User manager;

    public TeamResponse(Long id,
                         String name,
                         String location,
                         Date creationDate,
                         String description,
                         Set<User> players,
                         User manager) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.creationDate = creationDate;
        this.description = description;
        this.players = players;
        this.manager = manager;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public Set<User> getPlayers() {
        return players;
    }

    public void setPlayers(Set<User> players) {
        this.players = players;
    }
}
