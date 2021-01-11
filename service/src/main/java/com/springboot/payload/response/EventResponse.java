package com.springboot.payload.response;


import com.springboot.models.User;

import java.util.Set;

public class EventResponse {
    private Long id;
    private String city;
    private String address;
    private Set<User> participants;

    public EventResponse(Long id, String city, String address, Set<User> participants){

        this.id = id;
        this.city = city;
        this.address = address;
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }
}
