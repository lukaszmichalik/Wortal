package com.springboot.services;

import com.springboot.models.Event;
import com.springboot.payload.request.CreateEventRequest;
import com.springboot.payload.request.EventUserIdsRequest;
import com.springboot.payload.request.IdRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {

    ResponseEntity<?> getEvent(IdRequest idRequest);
    ResponseEntity<?> createEvent(CreateEventRequest createEventRequest);
    List<Event> getAllEvents();
    List<Event> getEventsNotAttendedByUser(IdRequest idRequest);
    ResponseEntity<?> deleteEvent(IdRequest idRequest);
    ResponseEntity<?> getUserEvents(IdRequest idRequest);
    ResponseEntity<?> addUserToEvent(EventUserIdsRequest eventUserIdsRequest);
    ResponseEntity<?> deleteUserFromEvent(EventUserIdsRequest eventUserIdsRequest);
}
