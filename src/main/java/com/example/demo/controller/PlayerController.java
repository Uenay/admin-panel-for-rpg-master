package com.example.demo.controller;

import com.example.demo.dto.CreatePlayerRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;

    @PostMapping("/player/create")
    public PlayerDto createPlayer(@RequestBody CreatePlayerRequest createPlayerRequest){
        return playerService.createPlayer;
    }

}
