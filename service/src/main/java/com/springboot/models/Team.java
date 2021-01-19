package com.springboot.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "teams",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String location;

    @Column
    private Date creationDate;


    @Size(max = 250)
    private String description;

    @JsonIgnore
    @OneToMany(cascade =CascadeType.ALL, mappedBy = "team")
    @JsonBackReference
    private Set<User> players= new HashSet<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;


    public Team() {
    }

    public Team(String name, String location, Date creationDate, String description) {
        this.name = name;
        this.location = location;
        this.creationDate = creationDate;
        this.description = description;
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

    public Set<User> getPlayers() {
        return players;
    }

    public void setPlayers(Set<User> players) {
        this.players = players;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
