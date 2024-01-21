package com.example.demo.api.request;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class UpdatePlayerRequest extends CreatePlayerRequest {
    private Long id;

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }
}
