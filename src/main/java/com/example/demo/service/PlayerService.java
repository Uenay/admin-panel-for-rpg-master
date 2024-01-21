package com.example.demo.service;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.api.request.UpdatePlayerRequest;

public interface PlayerService {
    PlayerDto createPlayer(PlayerDto createPlayerRequest);

    PlayerDto updatePlayer(PlayerDto updatePlayerRequest);
}
