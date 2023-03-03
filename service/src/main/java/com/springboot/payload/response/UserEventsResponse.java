package com.springboot.payload.response;

import com.springboot.models.Event;

import java.util.Set;

public class UserEventsResponse {
    private Set<Event> events;

    public UserEventsResponse(Set<Event> events) {
        this.events = events;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
