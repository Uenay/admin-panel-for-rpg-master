package com.example.demo.mapper;

import com.example.demo.entity.Player;
import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.dto.PlayerDto;

public class DtoMapper {
    public static PlayerDto convertToPlayerDto(CreatePlayerRequest createPlayerRequest) {
        return PlayerDto.builder()
                .name(createPlayerRequest.getName())
                .birthday(createPlayerRequest.getBirthday())
                .race(createPlayerRequest.getRace())
                .title(createPlayerRequest.getTitle())
                .level(createPlayerRequest.getLevel())
                .banned(createPlayerRequest.getBanned())
                .experience(createPlayerRequest.getExperience())
                .profession(createPlayerRequest.getProfession())
                .untilNextLevel(createPlayerRequest.getUntilNextLevel())
                .build();
    }

    public static CreatePlayerResponse convertToResponse(PlayerDto playerDto) {
        //tod
        return null;
    }

    public static Player converToPlayer(PlayerDto playerDto) {
        //todo
        return null;
    }

    public static PlayerDto converToPlayerDto(Player playerDto) {
        //todo
        return null;
    }
}
