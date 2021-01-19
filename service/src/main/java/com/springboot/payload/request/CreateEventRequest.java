package com.springboot.payload.request;

import com.springboot.models.ESurface;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Set;

public class CreateEventRequest {

    @NotBlank
    private String city;

    @NotBlank
    private String address;

    private Date date;

    @NotBlank
    private String time;

    @Enumerated(EnumType.STRING)
    private ESurface surface;

    @NotBlank
    private String limitation;

    @NotBlank
    @Size(min=3, max=250)
    private String description;

    private Set<Long> participants;

    private Long organizer_id;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ESurface getSurface() {
        return surface;
    }

    public void setSurface(ESurface surface) {
        this.surface = surface;
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

    public Long getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(Long organizer_id) {
        this.organizer_id = organizer_id;
    }

    public Set<Long> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Long> participants) {
        this.participants = participants;
    }
}
