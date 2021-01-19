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

//        if(manager.getTeam()==null) {
//            manager.setTeam(team);
//            userRepository.save(manager);
//        }else{
//            return ResponseEntity.badRequest()
//                    .body(new MessageResponse("Błąd: Ten użytkownik jest już managerem innej drużyny"));
//        }


        return ResponseEntity.ok(new MessageResponse("Twoja drużyna została poprawnie dodana!"));

    }

    @PostMapping("/deleteTeam")
    @ResponseBody
    public ResponseEntity<?> deleteTeam(@RequestBody IdRequest idRequest){

        Team team = teamRepository.getOne(idRequest.getId());

        Set<User> players = team.getPlayers();

        players.forEach(player->{
            System.out.print(player.getName());
            player.setTeam(null);
            userRepository.save(player);
        });

        if (teamRepository.existsById(idRequest.getId())) {
            teamRepository.deleteById(idRequest.getId());
            return ResponseEntity.ok(new MessageResponse("Poprawnie usunięto drużynę!"));
        }


        return ResponseEntity.badRequest()
                .body(new MessageResponse("Błąd: Drużyna o takim Id nie istnieje!"));
    }
}
