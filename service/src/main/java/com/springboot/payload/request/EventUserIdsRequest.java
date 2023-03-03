package com.springboot.payload.request;

import javax.validation.constraints.NotBlank;

public class EventUserIdsRequest {

    @NotBlank
    private Long userId;

    @NotBlank
    private Long eventId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
