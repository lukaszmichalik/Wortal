package com.springboot.controllers;

import com.springboot.models.Team;
import com.springboot.models.User;
import com.springboot.payload.request.CreateTeamRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.payload.request.TeamUserIdsRequest;
import com.springboot.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    TeamService teamService;


    @GetMapping("/allTeams")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    List<Team> allTeams(){

        return teamService.selectAllTeams();
    }

    @PostMapping("/createTeam")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createTeam(@RequestBody CreateTeamRequest createTeamRequest){

        return teamService.createNewTeam(createTeamRequest);
    }

    @PostMapping("/deleteTeam")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> deleteTeam(@RequestBody IdRequest idRequest){

        return teamService.removeTeam(idRequest);
    }

    @PostMapping("/getTeam")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getTeam(@RequestBody IdRequest idRequest){

        return teamService.selectTeam(idRequest);
    }

    @PostMapping("/getUserTeams")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserTeams(@RequestBody IdRequest idRequest) {

        return teamService.selectUserTeams(idRequest);
    }

    @PostMapping("/getUserManagedTeams")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserManagedTeams(@RequestBody IdRequest idRequest) {

        return teamService.selectUserManagedTeams(idRequest);
    }

    @GetMapping("/allUsersWithoutTeam")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    Set<User> allUsersWithoutTeam() {

        return teamService.selectAllUsersWithoutTeam();
    }

    @PostMapping("/addUserToTeam")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addUserToTeam(@RequestBody TeamUserIdsRequest teamUserIdsRequest) {

        return teamService.attachUserToTeam(teamUserIdsRequest);
    }

    @PostMapping("/deleteUserFromTeam")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserFromTeam(@RequestBody TeamUserIdsRequest teamUserIdsRequest) {

        return teamService.removeUserFromTeam(teamUserIdsRequest);
    }
}
