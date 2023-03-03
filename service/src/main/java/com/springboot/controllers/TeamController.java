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


    @GetMapping("/get-all-teams")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    List<Team> getAllTeams(){

        return teamService.getAllTeams();
    }

    @PostMapping("/create-team")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createTeam(@RequestBody CreateTeamRequest createTeamRequest) {

        return teamService.createTeam(createTeamRequest);
    }

    @PostMapping("/delete-team")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<?> deleteTeam(@RequestBody IdRequest idRequest){

        return teamService.deleteTeam(idRequest);
    }

    @PostMapping("/get-team")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getTeam(@RequestBody IdRequest idRequest){

        return teamService.getTeam(idRequest);
    }

    @PostMapping("/get-user-teams")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserTeams(@RequestBody IdRequest idRequest) {

        return teamService.getUserTeams(idRequest);
    }

    @PostMapping("/get-teams-managed-by-user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getTeamsManagedByUser(@RequestBody IdRequest idRequest) {

        return teamService.getTeamsManagedByUser(idRequest);
    }

    @GetMapping("/get-all-users-without-team")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    Set<User> getAllUsersWithoutTeam() {

        return teamService.getAllUsersWithoutTeam();
    }

    @PostMapping("/add-user-to-team")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addUserToTeam(@RequestBody TeamUserIdsRequest teamUserIdsRequest) {

        return teamService.addUserToTeam(teamUserIdsRequest);
    }

    @PostMapping("/delete-user-from-team")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteUserFromTeam(@RequestBody TeamUserIdsRequest teamUserIdsRequest) {

        return teamService.deleteUserFromTeam(teamUserIdsRequest);
    }
}
