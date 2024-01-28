package com.example.demo.api.controller;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.dto.PlayerDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PlayerController {
    @PostMapping("/player/create")
    CreatePlayerResponse createPlayer(@RequestBody CreatePlayerRequest createPlayerRequest);

    @GetMapping("/players/{id}")
    PlayerDto getPlayerById(@PathVariable("id") Long id);
    @GetMapping("/player")
    List<PlayerDto> getPlayers();
    @DeleteMapping("/player/delete")
    void deletePlayer(@PathVariable("id") Long id);
}
