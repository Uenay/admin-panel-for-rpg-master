package com.example.demo.api.controller;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PlayerControllerImplValidationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PlayerService playerService;

    private static final String CREATE_PLAYER_URL = "/rest/players";
    private static final String GET_PLAYER_URL = "/rest/players/{id}";
    private static final String UPDATE_PLAYER_URL = "/rest/players/{id}";
    private static final String DELETE_PLAYER_URL = "/rest/players/{id}";

    @Test
    void createWrongPlayerMvc() throws Exception {
        CreatePlayerRequest createPlayerRequest = CreatePlayerRequest.builder()
                .name("nameeeeeeeeeeeeee")
                .banned(true)
                .experience(12)
                .birthday(new Date(122,1, 1))
                .profession(Profession.ROGUE)
                .race(Race.DWARF)
                .title("titleeeeeeeeeeeeeeeeeeeeeeeeeeeee")
                .build();

        mockMvc.perform(
                        post(CREATE_PLAYER_URL)
                                .content(objectMapper.writeValueAsString(createPlayerRequest))
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPlayerByNotFoundIdMvc() throws Exception {
        mockMvc.perform(
                        get(GET_PLAYER_URL, 20000)
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getPlayerByWrongIdMvc() throws Exception {
        mockMvc.perform(
                        get(GET_PLAYER_URL, -2)
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePlayerByNotFoundIdMvc() throws Exception {
        UpdatePlayerRequest updatePlayerRequest = UpdatePlayerRequest.builder()
                .name("name1")
                .birthday(new Date(122, 1, 1))
                .race(Race.DWARF)
                .title("title1")
                .banned(false)
                .experience(12)
                .profession(Profession.ROGUE)
                .id(20000)
                .build();

        mockMvc.perform(
                        post(UPDATE_PLAYER_URL, 20000)
                                .content(objectMapper.writeValueAsString(updatePlayerRequest))
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePlayerByWrongIdMvc() throws Exception {
        UpdatePlayerRequest updatePlayerRequest = UpdatePlayerRequest.builder()
                .name("name1")
                .birthday(new Date(1, 1, 1))
                .race(Race.DWARF)
                .title("title1")
                .banned(false)
                .experience(12)
                .profession(Profession.ROGUE)
                .id(-2)
                .build();

        mockMvc.perform(
                        post(UPDATE_PLAYER_URL, 20000)
                                .content(objectMapper.writeValueAsString(updatePlayerRequest))
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void updateWrongPlayerMvc() throws Exception {
        PlayerDto playerDto = PlayerDto.builder()
                .name("name")
                .birthday(new Date(1, 1, 1))
                .race(Race.DWARF)
                .title("title")
                .level(1)
                .banned(false)
                .experience(11)
                .profession(Profession.ROGUE)
                .untilNextLevel(1)
                .build();

        PlayerDto createdPlayer = playerService.createPlayer(playerDto);

        UpdatePlayerRequest updatePlayerRequest = UpdatePlayerRequest.builder()
                .name("nameeeeeeeeeeeeee")
                .banned(true)
                .experience(12)
                .birthday(new Date(122,1, 1))
                .profession(Profession.ROGUE)
                .race(Race.DWARF)
                .title("titleeeeeeeeeeeeeeeeeeeeeeeeeeeee")
                .id(createdPlayer.getId())
                .build();

        mockMvc.perform(
                        post(UPDATE_PLAYER_URL, createdPlayer.getId())
                                .content(objectMapper.writeValueAsString(updatePlayerRequest))
                                .header("Content-Type", "application/json")

                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void deletePlayerByNotFoundIdMvc() throws Exception {
        mockMvc.perform(
                        delete(DELETE_PLAYER_URL, 20000)
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePlayerByWrongIdMvc() throws Exception {
        mockMvc.perform(
                        delete(DELETE_PLAYER_URL, -2)
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isBadRequest());
    }
}
