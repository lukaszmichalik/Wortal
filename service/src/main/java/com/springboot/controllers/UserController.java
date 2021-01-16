package com.springboot.controllers;

import com.springboot.models.Event;
import com.springboot.models.User;
import com.springboot.payload.request.EditUserRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.payload.response.MessageResponse;
import com.springboot.payload.response.UserEventsResponse;
import com.springboot.payload.response.UserResponse;
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
    PasswordEncoder encoder;


    @PostMapping("/editUser")
    public ResponseEntity<?> editUser(@RequestBody EditUserRequest editUser){

        User user = userRepository.getOne(editUser.getId());

        user.setName(editUser.getName());
        user.setPosition(editUser.getPosition());
        user.setDob(editUser.getDob());
        user.setEmail(editUser.getEmail());
        user.setUsername(editUser.getUsername());

        if(!editUser.getPassword().equals(""))
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

    @PostMapping("/getUserEvents")
    public ResponseEntity<?> getUserEvents(@RequestBody IdRequest idRequest){

        User user = userRepository.getOne(idRequest.getId());

//        Set<Event> userEvents = user.getEvents();
//
//        userEvents.forEach(event -> {
//            Integer size = event.getParticipants().size();
//        });

        return ResponseEntity.ok(new UserEventsResponse(user.getEvents()));
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody IdRequest idRequest){

        //User user = userRepository.getOne(idRequest.getId());
        if (userRepository.existsById(idRequest.getId())) {
            userRepository.deleteById(idRequest.getId());
            return ResponseEntity.ok(new MessageResponse("Rejestracja się powiodła!"));
        }
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Błąd: Użytkownik o takim Id nie istnieje w bazie!"));
    }

    @GetMapping("/allUsers")
    @ResponseBody
    List<User> allUsers(){
        return userRepository.findAll();
    }

}
