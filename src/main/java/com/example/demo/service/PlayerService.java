package com.example.demo.service;

import com.example.demo.api.request.PlayerFilter;
import com.example.demo.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    PlayerDto createPlayer(PlayerDto createPlayerRequest);

    PlayerDto updatePlayer(PlayerDto updatePlayerRequest);

    PlayerDto getPlayerById(Long id);


    void deletePlayer(Long id);

    List<PlayerDto> getFilteredPlayers(PlayerFilter playerFilter);

    int getFilteredPlayersCount(PlayerFilter playerFilter);
}

