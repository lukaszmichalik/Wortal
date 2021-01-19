package com.springboot.controllers;

import com.springboot.models.Event;
import com.springboot.models.Team;
import com.springboot.models.User;
import com.springboot.payload.request.CreateEventRequest;
import com.springboot.payload.request.CreateTeamRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.payload.response.MessageResponse;
import com.springboot.repository.TeamRepository;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/allTeams")
    @ResponseBody
    List<Team> allTeams(){
        return teamRepository.findAll();
    }

    @PostMapping("/createTeam")
    public ResponseEntity<?> createTeam(@RequestBody CreateTeamRequest createTeamRequest){

        User manager = userRepository.getOne(createTeamRequest.getManager_id());

        Team team = new Team(
                createTeamRequest.getName(),
                createTeamRequest.getLocation(),
                createTeamRequest.getCreationDate(),
                createTeamRequest.getDescription(),
                manager
        );



        teamRepository.save(team);
        Set<Long> longPlayers = createTeamRequest.getPlayers();

        longPlayers.forEach(id->{
            User player = userRepository.getOne(id);
            if(player.getTeam()==null) {
                player.setTeam(team);
                userRepository.save(player);
            }
        });

        manager.setTeam(team);

        userRepository.save(manager);

        return ResponseEntity.ok(new MessageResponse("Twoja drużyna została poprawnie dodana!"));

    }

}
