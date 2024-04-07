package com.example.demo.service;

import com.example.demo.api.request.PlayerFilter;
import com.example.demo.dto.PlayerDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface PlayerService {
    PlayerDto createPlayer(PlayerDto createPlayerRequest);

    PlayerDto updatePlayer(PlayerDto updatePlayerRequest);

    @Nullable
    PlayerDto getPlayerById(int id);


    void deletePlayer(int id);

    List<PlayerDto> getFilteredPlayers(PlayerFilter playerFilter);

    int getFilteredPlayersCount(PlayerFilter playerFilter);
}

