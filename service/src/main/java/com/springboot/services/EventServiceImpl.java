package com.springboot.services;

import com.springboot.models.Event;
import com.springboot.models.User;
import com.springboot.payload.request.CreateEventRequest;
import com.springboot.payload.request.EventUserIdsRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.payload.response.EventResponse;
import com.springboot.payload.response.MessageResponse;
import com.springboot.payload.response.UserEventsResponse;
import com.springboot.repository.EventRepository;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> getEvent(IdRequest idRequest) {

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

    public ResponseEntity<?> createEvent(CreateEventRequest createEventRequest) {

        User organizer = userRepository.getOne(createEventRequest.getOrganizer_id());

        Event event = new Event(
                createEventRequest.getCity(),
                createEventRequest.getAddress(),
                createEventRequest.getDate(),
                createEventRequest.getTime(),
                createEventRequest.getSurface(),
                createEventRequest.getLimitation(),
                createEventRequest.getDescription(),
                organizer
        );
        eventRepository.save(event);
        Set<Long> longParticipants = createEventRequest.getParticipants();

        longParticipants.forEach(id->{
            User participant = userRepository.getOne(id);
            Set<Event> participantsEvents = participant.getEvents();
            participantsEvents.add(event);
            participant.setEvents(participantsEvents);
            userRepository.save(participant);
        });

        return ResponseEntity.ok(new MessageResponse("Twoje wydarzenie zostało poprawnie opublikowane!"));
    }

    public List<Event> allEvents() {

        return eventRepository.findAll();
    }

    public List<Event> getEventsNotAttendedByUser(IdRequest idRequest) {

        List<Event> allEvents = eventRepository.findAll();
        User user = userRepository.getOne(idRequest.getId());
        Set<Event> userEvents = user.getEvents();
        userEvents.forEach(allEvents::remove);

        return allEvents;
    }

    public ResponseEntity<?> deleteEvent(IdRequest idRequest) {

        Event event = eventRepository.getOne(idRequest.getId());

        Set<User> participants = event.getParticipants();

        participants.forEach(participant->{
            Set<Event> userEvents = participant.getEvents();
            userEvents.remove(event);
            participant.setEvents(userEvents);
            userRepository.save(participant);
        });

        if (eventRepository.existsById(idRequest.getId())) {
            eventRepository.deleteById(idRequest.getId());
            return ResponseEntity.ok(new MessageResponse("Poprawnie usunięto wydarzenie!"));
        }


        return ResponseEntity.badRequest()
                .body(new MessageResponse("Błąd: Wydarzeni o takim Id nie istnieje!"));
    }

    public ResponseEntity<?> getUserEvents(IdRequest idRequest) {

        User user = userRepository.getOne(idRequest.getId());

        return ResponseEntity.ok(new UserEventsResponse(user.getEvents()));
    }

    public ResponseEntity<?> addUserToEvent(EventUserIdsRequest eventUserIdsRequest) {

        Event event = eventRepository.getOne(eventUserIdsRequest.getEventId());
        User user = userRepository.getOne(eventUserIdsRequest.getUserId());
        Set<Event> userEvents = user.getEvents();
        userEvents.add(event);
        user.setEvents(userEvents);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Poprawnie dodano Cię do wydarzenia !"));
    }

    public ResponseEntity<?> deleteUserFromEvent(EventUserIdsRequest eventUserIdsRequest) {

        Event event = eventRepository.getOne(eventUserIdsRequest.getEventId());
        User user = userRepository.getOne(eventUserIdsRequest.getUserId());
        Set<Event> userEvents = user.getEvents();
        userEvents.remove(event);
        user.setEvents(userEvents);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Poprawnie usunięto Cię z wydarzenia !"));
    }
}
