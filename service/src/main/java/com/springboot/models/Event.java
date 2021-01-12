package com.springboot.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
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

    @JsonFormat(pattern="dd-MMMM-yyyy HH:mm:ss", locale ="pl-PL",shape = JsonFormat.Shape.STRING)
    private String date;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ESurface surface;

    @Size(max = 50)
    private String limitation;

    @Size(max = 50)
    private String duration;

    @Size(max = 50)
    private String state;

    @Size(max = 250)
    private String description;


    @ManyToMany(mappedBy = "events")
    @JsonIgnore
    private Set<User> participants = new HashSet<>();
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="organizer_id")
    private User organizer_id;

    public Event() {
    }

    public Event(String city,
                 String address,
                 String date,
                 ESurface surface,
                 String limitation,
                 String duration,
                 String state,
                 String description
    ) {
        this.city = city;
        this.address = address;
        this.date = date;
        this.surface = surface;
        this.limitation = limitation;
        this.duration = duration;
        this.state = state;
       this.description = description;
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

    public User getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(User organizer_id) {
        this.organizer_id = organizer_id;
    }

    public ESurface getSurface() {
        return surface;
    }

    public void setSurface(ESurface surface) {
        this.surface = surface;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLimitation() {
        return limitation;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }
//
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


