package com.example.demo.api.controller;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.api.response.UpdatePlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PlayerControllerImplTest {
    @Autowired
    private PlayerController playerController;


    @Test
    void createPlayer() {
        CreatePlayerRequest createPlayerRequest = CreatePlayerRequest.builder()
                .name("name")
                .banned(true)
                .experience(12)
                .birthday(new Date())
                .profession(Profession.ROGUE)
                .race(Race.DWARF)
                .title("title")
                .build();
        CreatePlayerResponse createPlayerResponse = playerController.createPlayer(createPlayerRequest);

        assertEquals(createPlayerRequest.getName(), createPlayerResponse.getName());
        assertEquals(createPlayerRequest.getBanned(), createPlayerResponse.getBanned());
        assertEquals(createPlayerRequest.getExperience(), createPlayerResponse.getExperience());
        assertEquals(createPlayerRequest.getBirthday(), createPlayerResponse.getBirthday());
        assertEquals(createPlayerRequest.getProfession(), createPlayerResponse.getProfession());
        assertEquals(createPlayerRequest.getRace(), createPlayerResponse.getRace());
        assertEquals(createPlayerRequest.getTitle(), createPlayerResponse.getTitle());
    }


    @Test
    void getPlayerById() {
        Long id = 1L;
        PlayerDto playerDto = PlayerDto.builder()
                .name("name")
                .birthday(new Date())
                .race(Race.DWARF)
                .title("title")
                .level(1)
                .banned(false)
                .experience(11)
                .profession(Profession.ROGUE)
                .untilNextLevel(1)
                .id(1L)
                .build();
        GetPlayerResponse getPlayerResponse = playerController.getPlayerById(id);
        assertEquals(playerDto.getName(), getPlayerResponse.getName());
        assertEquals(playerDto.getBanned(), getPlayerResponse.getBanned());
        assertEquals(playerDto.getExperience(), getPlayerResponse.getExperience());
        assertEquals(playerDto.getBirthday(), getPlayerResponse.getBirthday());
        assertEquals(playerDto.getProfession().toString(), getPlayerResponse.getProfession().toString());
        assertEquals(playerDto.getRace().toString(), getPlayerResponse.getRace().toString());
        assertEquals(playerDto.getTitle(), getPlayerResponse.getTitle());
        assertEquals(playerDto.getLevel(), getPlayerResponse.getLevel());
        assertEquals(playerDto.getUntilNextLevel(), getPlayerResponse.getUntilNextLevel());
        assertEquals(playerDto.getId(), getPlayerResponse.getId());
    }

    @Test
    void deletePlayer() {
    }

    @Test
    void updatePlayer() {
        Long id = 2L;
        UpdatePlayerRequest updatePlayerRequest = UpdatePlayerRequest.builder()
                .name("name")
                .birthday(new Date())
                .race(Race.DWARF)
                .title("title")
                .banned(false)
                .experience(11)
                .profession(Profession.ROGUE)
                .id(2L)
                .build();
        UpdatePlayerResponse updatePlayerResponse = playerController.updatePlayer(id, updatePlayerRequest);
        assertEquals(updatePlayerResponse.getName(), updatePlayerRequest.getName());
        assertEquals(updatePlayerResponse.getBanned(), updatePlayerRequest.getBanned());
        assertEquals(updatePlayerResponse.getExperience(), updatePlayerRequest.getExperience());
        assertEquals(updatePlayerResponse.getBirthday(), updatePlayerRequest.getBirthday());
        assertEquals(updatePlayerResponse.getProfession().toString(), updatePlayerRequest.getProfession().toString());
        assertEquals(updatePlayerResponse.getRace().toString(), updatePlayerRequest.getRace().toString());
        assertEquals(updatePlayerResponse.getTitle(), updatePlayerRequest.getTitle());
        assertEquals(updatePlayerResponse.getId(), updatePlayerRequest.getId());
    }

    @Test
    void getFilteredPlayers() {
    }

    @Test
    void getFilteredPlayersCount() {
    }
}