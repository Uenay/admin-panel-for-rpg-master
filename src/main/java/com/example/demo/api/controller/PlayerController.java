package com.example.demo.api.controller;


import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.api.response.UpdatePlayerResponse;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface PlayerController {
    @PostMapping("/rest/players")
    CreatePlayerResponse createPlayer(@RequestBody CreatePlayerRequest createPlayerRequest);

    @GetMapping("/rest/players/{id}")
    GetPlayerResponse getPlayerById(@PathVariable("id") int id);

    @DeleteMapping("/rest/players/{id}")
    void deletePlayer(@PathVariable("id") int id);

    @PostMapping("/rest/players/{id}")
    UpdatePlayerResponse updatePlayer(@PathVariable("id") int id, @RequestBody UpdatePlayerRequest updatePlayerRequest);

    @GetMapping("/rest/players")
    List<GetPlayerResponse> getFilteredPlayers(@RequestParam(required = false) String name,
                                               @RequestParam(required = false) String title,
                                               @RequestParam(required = false) Race race,
                                               @RequestParam(required = false) Profession profession,
                                               @RequestParam(required = false) Date before,
                                               @RequestParam(required = false) Date after,
                                               @RequestParam(required = false, defaultValue = "ID") PlayerOrder order,
                                               @RequestParam(required = false) Long minExperience,
                                               @RequestParam(required = false) Long maxExperience,
                                               @RequestParam(required = false) Integer minLevel,
                                               @RequestParam(required = false) Integer maxLevel,
                                               @RequestParam(required = false) Boolean banned,
                                               @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                               @RequestParam(required = false, defaultValue = "3") Integer pageSize);

    @GetMapping("/rest/players/count")
    int getFilteredPlayersCount(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) Race race,
                                @RequestParam(required = false) Profession profession,
                                @RequestParam(required = false) Date before,
                                @RequestParam(required = false) Date after,
                                @RequestParam(required = false) Long minExperience,
                                @RequestParam(required = false) Long maxExperience,
                                @RequestParam(required = false) Integer minLevel,
                                @RequestParam(required = false) Integer maxLevel,
                                @RequestParam(required = false) Boolean banned);
}
