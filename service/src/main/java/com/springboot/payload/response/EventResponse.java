package com.springboot.payload.response;


import com.springboot.models.ESurface;
import com.springboot.models.User;

import java.sql.Date;
import java.util.Set;

public class EventResponse {
    private Long id;
    private String city;
    private String address;
    private Date date;
    private String time;
    private ESurface surface;
    private String limitation;
    private String description;
    private Set<User> participants;
    private User organizer;

    public EventResponse(Long id,
                         String city,
                         String address,
                         Date date,
                         String time,
                         ESurface surface,
                         String limitation,
                         String description,
                         Set<User> participants,
                         User organizer) {

        this.id = id;
        this.city = city;
        this.address = address;
        this.date = date;
        this.time = time;
        this.surface = surface;
        this.limitation = limitation;
        this.description = description;
        this.participants = participants;
        this.organizer = organizer;
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
        return organizer;
    }

    public void setOrganizer_id(User organizer) {
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
