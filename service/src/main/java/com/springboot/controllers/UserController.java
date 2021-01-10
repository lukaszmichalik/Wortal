package com.springboot.controllers;

import com.springboot.models.Event;
import com.springboot.models.User;
import com.springboot.payload.request.EditRequest;
import com.springboot.payload.request.UserIdRequest;
import com.springboot.payload.response.JwtResponse;
import com.springboot.payload.response.UserResponse;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<?> editUser(@RequestBody EditRequest editUser){

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

    @PostMapping("getEvents")
    public Set<Event> getEvents(@RequestBody UserIdRequest userIdRequest){

        User user = userRepository.getOne(userIdRequest.getId());

        userRepository.save(user);

        return user.getEvents();

    }
}
