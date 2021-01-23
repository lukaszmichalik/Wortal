package com.springboot.controllers;

import com.springboot.models.Team;
import com.springboot.models.User;
import com.springboot.payload.request.CreateTeamRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.payload.request.TeamUserIdsRequest;
import com.springboot.payload.response.*;
import com.springboot.repository.TeamRepository;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
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

        if (teamRepository.existsByName(createTeamRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Błąd: Taka nazwa drużyny już istnieje!"));
        }

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
            Set<Team> userTeams = player.getTeams();
            userTeams.add(team);
            player.setTeams(userTeams);
            userRepository.save(player);
        });

        Team teamupdate = teamRepository.findByName(createTeamRequest.getName());


        return ResponseEntity.ok(new IdResponse(teamupdate.getId()));

    }

    @PostMapping("/deleteTeam")
    @ResponseBody
    public ResponseEntity<?> deleteTeam(@RequestBody IdRequest idRequest){

        Team team = teamRepository.getOne(idRequest.getId());

        Set<User> players = team.getPlayers();

        players.forEach(player->{
            Set<Team> userTeams = player.getTeams();
            userTeams.remove(team);
            player.setTeams(userTeams);
            userRepository.save(player);

        });

        if (teamRepository.existsById(idRequest.getId())) {
            teamRepository.deleteById(idRequest.getId());
            return ResponseEntity.ok(new MessageResponse("Poprawnie usunięto drużynę!"));
        }


        return ResponseEntity.badRequest()
                .body(new MessageResponse("Błąd: Drużyna o takim Id nie istnieje!"));
    }

    @PostMapping("/getTeam")
    public ResponseEntity<?> getTeam(@RequestBody IdRequest idRequest){

        Team team = teamRepository.getOne(idRequest.getId());
        return ResponseEntity.ok(new TeamResponse(team.getId(),
                team.getName(),
                team.getLocation(),
                team.getCreationDate(),
                team.getDescription(),
                team.getPlayers(),
                team.getManager())
        );
    }

    @PostMapping("/getUserTeams")
    public ResponseEntity<?> getUserTeams(@RequestBody IdRequest idRequest) {

        User user = userRepository.getOne(idRequest.getId());

        return ResponseEntity.ok(new UserTeamsResponse(user.getTeams()));
    }

    @PostMapping("/getUserManagedTeams")
    public ResponseEntity<?> getUserManagedTeams(@RequestBody IdRequest idRequest) {

        User user = userRepository.getOne(idRequest.getId());

        return ResponseEntity.ok(new UserTeamsResponse(user.getManagedTeams()));
    }

    @GetMapping("/allUsersWithoutTeam")
    @ResponseBody
    Set<User> allUsersWithoutTeam() {

        List<User> allUsers = userRepository.findAll();
        Set<User> usersWithoutTeam = new HashSet<>();

        allUsers.forEach(user -> {
            if (user.getTeams() == null) {
                usersWithoutTeam.add(user);
            }
        });

        return usersWithoutTeam;
    }

    @PostMapping("/addUserToTeam")
    public ResponseEntity<?> addUserToTeam(@RequestBody TeamUserIdsRequest teamUserIdsRequest) {

        Team team = teamRepository.getOne(teamUserIdsRequest.getTeamId());
        User user = userRepository.getOne(teamUserIdsRequest.getUserId());
        Set<Team> userTeams = user.getTeams();
        userTeams.add(team);
        user.setTeams(userTeams);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Poprawnie dodano Cię do drużyny !"));

    }

    @PostMapping("/deleteUserFromTeam")
    public ResponseEntity<?> deleteUserFromTeam(@RequestBody TeamUserIdsRequest teamUserIdsRequest) {

        Team team = teamRepository.getOne(teamUserIdsRequest.getTeamId());
        User user = userRepository.getOne(teamUserIdsRequest.getUserId());
        Set<Team> userTeams = user.getTeams();
        userTeams.remove(team);
        user.setTeams(userTeams);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Poprawnie usunięto Cię z wydarzenia !"));

    }


}
