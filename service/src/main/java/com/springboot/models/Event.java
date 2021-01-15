package com.springboot.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "events")
public class Event implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String address;

    @JsonFormat(pattern="dd-MM-yyyy", locale ="pl-PL")
    private Date date;

    @NotBlank
    private String time;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ESurface surface;

    @Size(max = 50)
    private String limitation;

    @Size(max = 250)
    private String description;


    @ManyToMany(mappedBy = "events")
    @JsonIgnore
    private Set<User> participants = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="organizer")
    private User organizer;

    public Event() {
    }

    public Event(String city,
                 String address,
                 Date date,
                 String time,
                 ESurface surface,
                 String limitation,
                 String description,
                 User organizer

    ) {
        this.city = city;
        this.address = address;
        this.date = date;
        this.time = time;
        this.surface = surface;
        this.limitation = limitation;
        this.description = description;
        this.organizer = organizer;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public ESurface getSurface() {
        return surface;
    }

    public void setSurface(ESurface surface) {
        this.surface = surface;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLimitation() {
        return limitation;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


