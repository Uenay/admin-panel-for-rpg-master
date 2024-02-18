package com.example.demo.api.controller;


import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.PlayerFilter;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.api.response.UpdatePlayerResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PlayerController {
    @PostMapping("/rest/player/create")
    CreatePlayerResponse createPlayer(@RequestBody CreatePlayerRequest createPlayerRequest);

    @GetMapping("/rest/players/{id}")
    GetPlayerResponse getPlayerById(@PathVariable("id") Long id);
    @GetMapping("/rest/player")
    List<GetPlayerResponse> getPlayers();
    @DeleteMapping("/rest/player/delete/{id}")
    void deletePlayer(@PathVariable("id") Long id);

    @PostMapping("/rest/players/{id}")
    UpdatePlayerResponse updatePlayer(@PathVariable("id") Long id, @RequestBody UpdatePlayerRequest updatePlayerRequest);
    @GetMapping("/rest/players")
    List<GetPlayerResponse> getFilteredPlayers(@RequestBody PlayerFilter playerFilter);
    @GetMapping("/rest/players/count")
    int getFilteredPlayersCount(@RequestBody PlayerFilter playerFilter);
}
