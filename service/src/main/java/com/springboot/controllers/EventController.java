package com.springboot.controllers;

import com.springboot.models.Event;
import com.springboot.payload.request.IdRequest;
import com.springboot.payload.response.EventResponse;
import com.springboot.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @PostMapping("/getEvent")
    public ResponseEntity<?> getEvent(@RequestBody IdRequest idRequest){

        Event event = eventRepository.getOne(idRequest.getId());
        return ResponseEntity.ok(new EventResponse(event.getId(),
                event.getCity(),
                event.getAddress(),
                event.getDate(),
                event.getSurface(),
                event.getLimitation(),
                event.getDuration(),
                event.getState(),
                event.getDescription(),
                event.getParticipants(),
                event.getOrganizer_id())
        );
    }

//    @PostMapping("/getEvent")
//    public Set<User> getEvent(@RequestBody IdRequest idRequest){
//
//        Event event = eventRepository.getOne(idRequest.getId());
//
//
//
//        return event.getParticipants();
//    }
}
