package com.springboot.payload.response;

public class IdResponse {
    private Long id;

    public IdResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
