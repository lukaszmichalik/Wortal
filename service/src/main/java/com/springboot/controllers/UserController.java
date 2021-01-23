package com.springboot.controllers;

import com.springboot.models.Event;
import com.springboot.models.User;
import com.springboot.payload.request.EditUserRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.payload.response.MessageResponse;
import com.springboot.payload.response.UserResponse;
import com.springboot.repository.EventRepository;
import com.springboot.repository.TeamRepository;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PasswordEncoder encoder;


    @PostMapping("/editUser")
    public ResponseEntity<?> editUser(@RequestBody EditUserRequest editUser) {

        User user = userRepository.getOne(editUser.getId());

        user.setName(editUser.getName());
        user.setPosition(editUser.getPosition());
        user.setDob(editUser.getDob());
        user.setEmail(editUser.getEmail());
        user.setUsername(editUser.getUsername());

        if (!editUser.getPassword().equals(""))
            user.setPassword(encoder.encode(editUser.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(new UserResponse(user.getId(),
                user.getName(),
                user.getPosition(),
                user.getDob(),
                user.getEmail(),
                user.getUsername())
        );
    }



    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody IdRequest idRequest) {

        User user = userRepository.getOne(idRequest.getId());

        user.setEvents(null);

        userRepository.save(user);

        Set<Event> ownedEvents = user.getOwnedEvents();

        ownedEvents.forEach(event -> {
            if(eventRepository.existsById(event.getId())) {
                Set<User> participants = event.getParticipants();
                Event event2 = eventRepository.getOne(event.getId());


                participants.forEach(participant -> {
                    if(userRepository.existsById(participant.getId())) {
                        Set<Event> userEvents = participant.getEvents();
                        userEvents.remove(event2);
                        participant.setEvents(userEvents);
                        userRepository.save(participant);

                    }
                });
            }
        });

        if (userRepository.existsById(idRequest.getId())) {
            userRepository.deleteById(idRequest.getId());
            return ResponseEntity.ok(new MessageResponse("Poprawnie usunięto użytkonika !"));
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Błąd: Użytkownik o takim Id nie istnieje w bazie!"));
    }

    @GetMapping("/allUsers")
    @ResponseBody
    List<User> allUsers() {
        return userRepository.findAll();
    }


}
