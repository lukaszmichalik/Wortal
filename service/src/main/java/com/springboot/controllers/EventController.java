package com.springboot.controllers;

import com.springboot.models.Event;
import com.springboot.models.User;
import com.springboot.payload.request.CreateEventRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.payload.response.EventResponse;
import com.springboot.payload.response.MessageResponse;
import com.springboot.repository.EventRepository;

import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/getEvent")
    public ResponseEntity<?> getEvent(@RequestBody IdRequest idRequest){

        Event event = eventRepository.getOne(idRequest.getId());
        return ResponseEntity.ok(new EventResponse(event.getId(),
                event.getCity(),
                event.getAddress(),
                event.getDate(),
                event.getTime(),
                event.getSurface(),
                event.getLimitation(),
                event.getDescription(),
                event.getParticipants(),
                event.getOrganizer())
        );
    }

    @PostMapping("/createEvent")
    public ResponseEntity<?> createEvent(@RequestBody CreateEventRequest createEventRequest){

        User user = userRepository.getOne(createEventRequest.getOrganizer_id());

     
        Event event = new Event(
                createEventRequest.getCity(),
                createEventRequest.getAddress(),
                createEventRequest.getDate(),
                createEventRequest.getTime(),
                createEventRequest.getSurface(),
                createEventRequest.getLimitation(),
                createEventRequest.getDescription(),
                createEventRequest.getParticipants(),
                user
        );

        eventRepository.save(event);


        return ResponseEntity.ok(new MessageResponse("ok"));



//        return ResponseEntity.ok(new EventResponse(event.getId(),
//                event.getCity(),
//                event.getAddress(),
//                event.getDate(),
//                event.getSurface(),
//                event.getLimitation(),
//                event.getDescription(),
//                event.getParticipants(),
//                event.getOrganizer())
//        );
    }

}
