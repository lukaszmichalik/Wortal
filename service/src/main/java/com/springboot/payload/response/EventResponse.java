package com.springboot.payload.response;


import com.springboot.models.ESurface;
import com.springboot.models.User;

import java.util.Set;

public class EventResponse {
    private Long id;
    private String city;
    private String address;
    private String date;
    private ESurface surface;
    private String limitation;
    private String duration;
    private String state;
    private String description;
    private Set<User> participants;
    private User organizer_id;

    public EventResponse(Long id,
                         String city,
                         String address,
                         String date,
                         ESurface surface,
                         String limitation,
                         String duration,
                         String state,
                         String description,
                         Set<User> participants,
                         User organizer_id)
    {

        this.id = id;
        this.city = city;
        this.address = address;
        this.date = date;
        this.surface = surface;
        this.limitation = limitation;
        this.duration = duration;
        this.state = state;
        this.description = description;
        this.participants = participants;
        this.organizer_id = organizer_id;
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
