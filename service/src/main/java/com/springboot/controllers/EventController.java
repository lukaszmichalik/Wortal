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

    @PostMapping("/getEvent")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getEvent(@RequestBody IdRequest idRequest) {

        return eventService.getEvent(idRequest);
    }

    @PostMapping("/createEvent")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest createEventRequest) {

        return eventService.createEvent(createEventRequest);

    }

    @GetMapping("/allEvents")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    List<Event> allEvents() {

        return eventService.allEvents();
    }

    @PostMapping("/notAttendedEvents")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<Event> getEventsNotAttendedByUser(@RequestBody IdRequest idRequest) {

        return eventService.getEventsNotAttendedByUser(idRequest);
    }

    @PostMapping("/deleteEvent")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> deleteEvent(@RequestBody IdRequest idRequest) {

        return eventService.deleteEvent(idRequest);
    }

    @PostMapping("/getUserEvents")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserEvents(@RequestBody IdRequest idRequest) {

        return eventService.getUserEvents(idRequest);
    }

    @PostMapping("/addUserToEvent")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addUserToEvent(@RequestBody EventUserIdsRequest eventUserIdsRequest) {

        return eventService.addUserToEvent(eventUserIdsRequest);
    }

    @PostMapping("/deleteUserFromEvent")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserFromEvent(@RequestBody EventUserIdsRequest eventUserIdsRequest) {

        return eventService.deleteUserFromEvent(eventUserIdsRequest);
    }
}
