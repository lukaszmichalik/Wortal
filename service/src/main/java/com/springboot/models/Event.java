package com.springboot.models;


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

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ESurface surface;

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

    public Event( String city, String address, ESurface surface) {
        this.city = city;
        this.address = address;
        this.surface = surface;
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
}
