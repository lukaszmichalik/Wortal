package com.springboot.controllers;

import com.springboot.models.Team;
import com.springboot.models.User;
import com.springboot.payload.request.IdRequest;
import com.springboot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;


    @GetMapping("/allTeams")
    @ResponseBody
    List<Team> allTeams(){
        return teamRepository.findAll();
    }

}
