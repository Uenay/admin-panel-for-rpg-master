package com.example.demo.api.controller;

import com.example.demo.mapper.DtoMapper;
import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.service.PlayerService;
import liquibase.pro.packaged.L;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerControllerImpl implements PlayerController {
    private final PlayerService playerService;

    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
        PlayerDto playerDto = DtoMapper.convertToPlayerDto(createPlayerRequest);
        PlayerDto createdPlayer = playerService.createPlayer(playerDto);
        return DtoMapper.convertToResponse(createdPlayer);
    }

    public PlayerDto getPlayerById(Long id) {
        return playerService.getPlayerById(id);
    }
    public List<PlayerDto> getPlayers(){
        List<PlayerDto> playerDtoList = playerService.getPlayers();
        return playerDtoList;
    }
    public void deletePlayer(Long id){
        playerService.deletePlayer(id);
    }
}
