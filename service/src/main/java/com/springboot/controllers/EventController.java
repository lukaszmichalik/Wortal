package com.springboot.controllers;

import com.springboot.models.Event;
import com.springboot.payload.request.CreateEventRequest;
import com.springboot.payload.request.EventUserIdsRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/get-event")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getEvent(@RequestBody IdRequest idRequest) {

        return eventService.getEvent(idRequest);
    }

    @PostMapping("/create-event")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest createEventRequest) {

        return eventService.createEvent(createEventRequest);

    }

    @GetMapping("/get-all-events")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    List<Event> getAllEvents() {

        return eventService.getAllEvents();
    }

    @PostMapping("/get-events-not-attended-by-user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Event> getEventsNotAttendedByUser(@RequestBody IdRequest idRequest) {

        return eventService.getEventsNotAttendedByUser(idRequest);
    }

    @PostMapping("/delete-event")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> deleteEvent(@RequestBody IdRequest idRequest) {

        return eventService.deleteEvent(idRequest);
    }

    @PostMapping("/get-user-events")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserEvents(@RequestBody IdRequest idRequest) {

        return eventService.getUserEvents(idRequest);
    }

    @PostMapping("/add-user-to-event")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addUserToEvent(@RequestBody EventUserIdsRequest eventUserIdsRequest) {

        return eventService.addUserToEvent(eventUserIdsRequest);
    }

    @PostMapping("/delete-user-from-event")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserFromEvent(@RequestBody EventUserIdsRequest eventUserIdsRequest) {

        return eventService.deleteUserFromEvent(eventUserIdsRequest);
    }
}
