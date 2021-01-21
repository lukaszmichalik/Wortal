package com.springboot.payload.response;

import com.springboot.models.Team;

import java.util.Set;

public class UserTeamsResponse {
    private Set<Team> teams;

    public UserTeamsResponse(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
