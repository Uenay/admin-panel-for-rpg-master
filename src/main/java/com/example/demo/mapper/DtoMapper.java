package com.example.demo.mapper;

import com.example.demo.entity.*;
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
                .banned(createPlayerRequest.getBanned())
                .profession(createPlayerRequest.getProfession())
                .build();
    }

    public static CreatePlayerResponse convertToResponse(PlayerDto playerDto) {
        return CreatePlayerResponse.builder()
                .name(playerDto.getName())
                .birthday(playerDto.getBirthday())
                .race(playerDto.getRace())
                .title(playerDto.getTitle())
                .level(playerDto.getLevel())
                .banned(playerDto.getBanned())
                .experience(playerDto.getExperience())
                .profession(playerDto.getProfession())
                .untilNextLevel(playerDto.getUntilNextLevel())
                .build();
    }

    public static Player convertToPlayer(PlayerDto playerDto) {
        Player player = new Player();
        player.setName(playerDto.getName());
        player.setTitle(playerDto.getTitle());
        player.setLevel(playerDto.getLevel());
        player.setBirthday(playerDto.getBirthday());
        RaceEntity race = new RaceEntity();
        race.setName(playerDto.getRace().toString());
        player.setRace(race);
        ProfessionEntity profession = new ProfessionEntity();
        profession.setName(playerDto.getProfession().toString());
        player.setProfession(profession);
        player.setExperience(playerDto.getExperience());
        player.setBanned(playerDto.getBanned());
        player.setUntilNextLevel(playerDto.getUntilNextLevel());
        return player;
    }

    public static PlayerDto convertToPlayerDto(Player player) {
        Race race = Race.valueOf(player.getRace().getName());
        Profession profession = Profession.valueOf(player.getProfession().getName());
        return PlayerDto.builder()
                .id(player.getId())
                .name(player.getName())
                .birthday(player.getBirthday())
                .race(race)
                .title(player.getTitle())
                .level(player.getLevel())
                .banned(player.getBanned())
                .experience(player.getExperience())
                .profession(profession)
                .untilNextLevel(player.getUntilNextLevel())
                .build();
    }
}
