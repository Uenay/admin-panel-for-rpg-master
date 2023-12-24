package com.example.demo.service;

import com.example.demo.Player;
import com.example.demo.dto.CreatePlayerRequest;
import com.example.demo.dto.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService{
    @Override
    public PlayerDto createPlayer(CreatePlayerRequest createPlayerRequest) {
        Player player = new Player();
        return null;
    }
}
