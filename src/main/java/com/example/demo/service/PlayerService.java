package com.example.demo.service;

import com.example.demo.dto.CreatePlayerRequest;
import com.example.demo.dto.PlayerDto;

public interface PlayerService {
PlayerDto createPlayer(CreatePlayerRequest createPlayerRequest);
}
