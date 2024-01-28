package com.example.demo.service;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.api.request.UpdatePlayerRequest;

import java.util.List;

public interface PlayerService {
    PlayerDto createPlayer(PlayerDto createPlayerRequest);

    PlayerDto updatePlayer(PlayerDto updatePlayerRequest);

    PlayerDto getPlayerById(Long id);

    List<PlayerDto> getPlayers();

    void deletePlayer(Long id);


}
