package com.springboot.services;

import com.springboot.models.User;
import com.springboot.payload.request.EditUserRequest;
import com.springboot.payload.request.IdRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<?> editUser(EditUserRequest editUser);
    ResponseEntity<?> deleteUser(IdRequest idRequest);
    List<User> allUsers();
}
