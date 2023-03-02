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
    List<Team> getAllTeams();
    ResponseEntity<?> createTeam(CreateTeamRequest createTeamRequest);
    ResponseEntity<?> deleteTeam(IdRequest idRequest);
    ResponseEntity<?> getTeam(IdRequest idRequest);
    ResponseEntity<?> getUserTeams(IdRequest idRequest);
    ResponseEntity<?> getTeamsManagedByUser(IdRequest idRequest);
    Set<User> getAllUsersWithoutTeam();
    ResponseEntity<?> addUserToTeam(TeamUserIdsRequest teamUserIdsRequest);
    ResponseEntity<?> deleteUserFromTeam(TeamUserIdsRequest teamUserIdsRequest);
}
