package com.springboot.controllers;

import com.springboot.models.User;
import com.springboot.payload.request.EditRequest;
import com.springboot.payload.response.JwtResponse;
import com.springboot.payload.response.UserResponse;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
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

        System.out.println("this is password" + editUser.getPassword());
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

}
