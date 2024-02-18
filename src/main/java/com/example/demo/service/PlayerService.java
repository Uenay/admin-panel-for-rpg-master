package com.example.demo.service;

import com.example.demo.api.request.GetFilteredPlayersRequest;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    PlayerDto createPlayer(PlayerDto createPlayerRequest);

    PlayerDto updatePlayer(PlayerDto updatePlayerRequest);

    PlayerDto getPlayerById(Long id);

    List<GetPlayerResponse> getPlayers();

    void deletePlayer(Long id);

    List<GetPlayerResponse> getFilteredPlayers(GetFilteredPlayersRequest getFilteredPlayersRequest);

    int getFilteredPlayersCount(GetFilteredPlayersRequest getFilteredPlayersRequest);
}

