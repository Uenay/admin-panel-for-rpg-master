package com.example.demo.api.controller;

import com.example.demo.mapper.DtoMapper;
import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PlayerControllerImpl {
    private final PlayerService playerService;

    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest){
        PlayerDto playerDto = DtoMapper.convertToPlayerDto(createPlayerRequest);
        PlayerDto createdPlayer = playerService.createPlayer(playerDto);
        return DtoMapper.convertToResponse(createdPlayer);
    }
}
