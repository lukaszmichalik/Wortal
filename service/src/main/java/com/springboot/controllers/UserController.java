package com.springboot.controllers;

import com.springboot.models.User;
import com.springboot.payload.request.EditUserRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/editUser")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> editUser(@RequestBody EditUserRequest editUser) {

        return userService.editUser(editUser);
    }

    @PostMapping("/deleteUser")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestBody IdRequest idRequest) {

        return userService.deleteUser(idRequest);
    }

    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    List<User> allUsers() {
        return userService.allUsers();
    }
}
