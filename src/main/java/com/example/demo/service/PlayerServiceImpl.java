package com.example.demo.service;


import com.example.demo.entity.Player;
import com.example.demo.dto.PlayerDto;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.repository.PlayerRepository;
import liquibase.pro.packaged.P;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player = DtoMapper.convertToPlayer(playerDto);
        Player savedPlayer = playerRepository.save(player);
        return DtoMapper.convertToPlayerDto(savedPlayer);
    }

    @Override
    public PlayerDto getPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow();
        return DtoMapper.convertToPlayerDto(player);
    }

    @Override
    public List<PlayerDto> getPlayers() {
        List<Player> players = playerRepository.findAll();
        return null;
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepository.delete(playerRepository.findById(id).orElseThrow());
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto updatePlayerRequest) {
//        Player player = playerRepository.findById(updatePlayerRequest.getId()).orElseThrow();
        return null;
    }
}
