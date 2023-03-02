package com.springboot.services;

import com.springboot.models.Team;
import com.springboot.models.User;
import com.springboot.payload.request.CreateTeamRequest;
import com.springboot.payload.request.IdRequest;
import com.springboot.payload.request.TeamUserIdsRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface TeamService {
    List<Team> selectAllTeams();
    ResponseEntity<?> createNewTeam(CreateTeamRequest createTeamRequest);
    ResponseEntity<?> removeTeam(IdRequest idRequest);
    ResponseEntity<?> selectTeam(IdRequest idRequest);
    ResponseEntity<?> selectUserTeams(IdRequest idRequest);
    ResponseEntity<?> selectUserManagedTeams(IdRequest idRequest);
    Set<User> selectAllUsersWithoutTeam();
    ResponseEntity<?> attachUserToTeam(TeamUserIdsRequest teamUserIdsRequest);
    ResponseEntity<?> removeUserFromTeam(TeamUserIdsRequest teamUserIdsRequest);
}
