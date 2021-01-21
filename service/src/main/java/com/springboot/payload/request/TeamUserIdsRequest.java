package com.springboot.payload.request;

import javax.validation.constraints.NotBlank;

public class TeamUserIdsRequest {
    @NotBlank
    private Long userId;

    @NotBlank
    private Long teamId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
