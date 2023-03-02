package com.springboot.services;

import com.springboot.models.Team;
import com.springboot.models.User;
import com.springboot.payload.request.CreateTeamRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.payload.request.TeamUserIdsRequest;
import com.springboot.payload.response.IdResponse;
import com.springboot.payload.response.MessageResponse;
import com.springboot.payload.response.TeamResponse;
import com.springboot.payload.response.UserTeamsResponse;
import com.springboot.repository.TeamRepository;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Team> selectAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public ResponseEntity<?> createNewTeam(CreateTeamRequest createTeamRequest) {

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

    public ResponseEntity<?> removeTeam(IdRequest idRequest) {

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

    public ResponseEntity<?> selectTeam(IdRequest idRequest) {
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

    public ResponseEntity<?> selectUserTeams(IdRequest idRequest) {
        User user = userRepository.getOne(idRequest.getId());

        return ResponseEntity.ok(new UserTeamsResponse(user.getTeams()));
    }

    public ResponseEntity<?> selectUserManagedTeams(IdRequest idRequest) {
        User user = userRepository.getOne(idRequest.getId());

        return ResponseEntity.ok(new UserTeamsResponse(user.getManagedTeams()));
    }

    public Set<User> selectAllUsersWithoutTeam() {
        List<User> allUsers = userRepository.findAll();
        Set<User> usersWithoutTeam = new HashSet<>();

        allUsers.forEach(user -> {
            if (user.getTeams() == null) {
                usersWithoutTeam.add(user);
            }
        });

        return usersWithoutTeam;
    }

    public ResponseEntity<?> attachUserToTeam(TeamUserIdsRequest teamUserIdsRequest) {
        Team team = teamRepository.getOne(teamUserIdsRequest.getTeamId());
        User user = userRepository.getOne(teamUserIdsRequest.getUserId());
        Set<Team> userTeams = user.getTeams();
        userTeams.add(team);
        user.setTeams(userTeams);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Poprawnie dodano Cię do drużyny !"));
    }

    public ResponseEntity<?> removeUserFromTeam(TeamUserIdsRequest teamUserIdsRequest) {
        Team team = teamRepository.getOne(teamUserIdsRequest.getTeamId());
        User user = userRepository.getOne(teamUserIdsRequest.getUserId());
        Set<Team> userTeams = user.getTeams();
        userTeams.remove(team);
        user.setTeams(userTeams);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Poprawnie usunięto Cię z wydarzenia !"));
    }
}
