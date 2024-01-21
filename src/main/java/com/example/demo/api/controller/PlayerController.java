package com.example.demo.api.controller;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PlayerController {
    @PostMapping("/player/create")
    CreatePlayerResponse createPlayer(@RequestBody CreatePlayerRequest createPlayerRequest);
}
