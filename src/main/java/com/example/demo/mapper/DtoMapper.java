package com.example.demo.mapper;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.api.response.UpdatePlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.ProfessionEntity;
import com.example.demo.entity.Race;
import com.example.demo.entity.RaceEntity;

import java.util.ArrayList;
import java.util.List;

public class DtoMapper {
    public static PlayerDto convertToPlayerDto(CreatePlayerRequest createPlayerRequest) {
        return PlayerDto.builder()
                .name(createPlayerRequest.getName())
                .birthday(createPlayerRequest.getBirthday())
                .race(createPlayerRequest.getRace())
                .title(createPlayerRequest.getTitle())
                .banned(createPlayerRequest.getBanned())
                .profession(createPlayerRequest.getProfession())
                .experience(createPlayerRequest.getExperience())
                .build();
    }

    public static PlayerDto convertToPlayerDto(UpdatePlayerRequest updatePlayerRequest) {
        return PlayerDto.builder()
                .id(updatePlayerRequest.getId())
                .name(updatePlayerRequest.getName())
                .birthday(updatePlayerRequest.getBirthday())
                .race(updatePlayerRequest.getRace())
                .title(updatePlayerRequest.getTitle())
                .banned(updatePlayerRequest.getBanned())
                .profession(updatePlayerRequest.getProfession())
                .experience(updatePlayerRequest.getExperience())
                .build();
    }

    public static CreatePlayerResponse convertToCreateResponse(PlayerDto playerDto) {
        return CreatePlayerResponse.builder()
                .name(playerDto.getName())
                .birthday(playerDto.getBirthday().getTime())
                .race(playerDto.getRace())
                .title(playerDto.getTitle())
                .level(playerDto.getLevel())
                .banned(playerDto.getBanned())
                .experience(playerDto.getExperience())
                .profession(playerDto.getProfession())
                .untilNextLevel(playerDto.getUntilNextLevel())
                .id(playerDto.getId())
                .build();
    }

    public static UpdatePlayerResponse convertToUpdateResponse(PlayerDto playerDto) {
        return UpdatePlayerResponse.builder()
                .name(playerDto.getName())
                .birthday(playerDto.getBirthday().getTime())
                .race(playerDto.getRace())
                .title(playerDto.getTitle())
                .level(playerDto.getLevel())
                .banned(playerDto.getBanned())
                .experience(playerDto.getExperience())
                .profession(playerDto.getProfession())
                .untilNextLevel(playerDto.getUntilNextLevel())
                .id(playerDto.getId())
                .build();
    }

    public static GetPlayerResponse convertToGetResponse(PlayerDto playerDto) {
        return GetPlayerResponse.builder()
                .id(playerDto.getId())
                .name(playerDto.getName())
                .birthday(playerDto.getBirthday().getTime())
                .race(playerDto.getRace())
                .title(playerDto.getTitle())
                .level(playerDto.getLevel())
                .banned(playerDto.getBanned())
                .experience(playerDto.getExperience())
                .profession(playerDto.getProfession())
                .untilNextLevel(playerDto.getUntilNextLevel())
                .build();
    }

    public static List<GetPlayerResponse> convertToGetResponse(List<PlayerDto> playerDtos) {
        List<GetPlayerResponse> getPlayerResponseList = new ArrayList<>();

        for (PlayerDto playerDto : playerDtos) {
            GetPlayerResponse getPlayerResponse = GetPlayerResponse.builder()
                    .id(playerDto.getId())
                    .name(playerDto.getName())
                    .title(playerDto.getTitle())
                    .race(playerDto.getRace())
                    .profession(playerDto.getProfession())
                    .experience(playerDto.getExperience())
                    .level(playerDto.getLevel())
                    .untilNextLevel(playerDto.getUntilNextLevel())
                    .birthday(playerDto.getBirthday().getTime())
                    .banned(playerDto.getBanned())
                    .build();

            getPlayerResponseList.add(getPlayerResponse);
        }
        return getPlayerResponseList;
    }


    public static Player convertToPlayer(PlayerDto playerDto) {
        Player player = new Player();
        player.setId(playerDto.getId());
        player.setName(playerDto.getName());
        player.setTitle(playerDto.getTitle());
        player.setLevel(playerDto.getLevel());
        player.setRace(convertToRace(playerDto.getRace()));
        player.setProfession(convertToProfession(playerDto.getProfession()));
        player.setBirthday(playerDto.getBirthday());
        player.setExperience(playerDto.getExperience());
        player.setBanned(playerDto.getBanned());
        player.setUntilNextLevel(playerDto.getUntilNextLevel());
        return player;
    }

    public static RaceEntity convertToRace(Race race) {
        RaceEntity entity = new RaceEntity();
        entity.setName(race.name());
        return entity;
    }

    public static ProfessionEntity convertToProfession(Profession profession) {
        ProfessionEntity entity = new ProfessionEntity();
        entity.setName(profession.name());
        return entity;
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
