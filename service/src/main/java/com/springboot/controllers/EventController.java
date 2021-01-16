package com.springboot.controllers;

import com.springboot.models.Event;
import com.springboot.models.Role;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
                user
        );
        eventRepository.save(event);
        Set<Long> longParticipants = createEventRequest.getParticipants();
        Set<User> participants = new HashSet<>();

        longParticipants.forEach(id->{
            System.out.print(id);
            User participant = userRepository.getOne(id);
            Set<Event> participantsEvents = participant.getEvents();
            participantsEvents.add(event);
            participant.setEvents(participantsEvents);
            userRepository.save(participant);
        });

        return ResponseEntity.ok(new MessageResponse("Twoje wydarzenie zostało poprawinie opublikowane!"));

    }

    @GetMapping("/allEvents")
    @ResponseBody
    List<Event> allEvents(){
        return eventRepository.findAll();
    }

}
