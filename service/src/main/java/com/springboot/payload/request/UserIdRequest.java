package com.springboot.payload.request;

import javax.validation.constraints.NotBlank;

public class UserIdRequest {

    @NotBlank
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
