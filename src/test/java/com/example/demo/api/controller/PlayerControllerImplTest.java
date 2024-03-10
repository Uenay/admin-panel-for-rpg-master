package com.example.demo.api.controller;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PlayerControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PlayerController playerController;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String CREATE_PLAYER_URL = "/rest/player/create";
    private static final String GET_PLAYER_URL = "/rest/players/{id}";
    private static final String UPDATE_PLAYER_URL = "/rest/players/{id}";
    private static final String GET_FILTERED_PLAYER_URL = "/rest/players";

    @Test
    void createPlayerMvc() throws Exception {
        CreatePlayerRequest createPlayerRequest = CreatePlayerRequest.builder()
                .name("name")
                .banned(true)
                .experience(12)
                .birthday(new Date(1,1, 1))
                .profession(Profession.ROGUE)
                .race(Race.DWARF)
                .title("title")
                .build();


        mockMvc.perform(
                post(CREATE_PLAYER_URL)
                        .content(objectMapper.writeValueAsString(createPlayerRequest))
                        .header("Content-Type", "application/json")

        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(createPlayerRequest.getName()))
                .andExpect(jsonPath("$.banned").value(createPlayerRequest.getBanned()))
                .andExpect(jsonPath("$.experience").value(createPlayerRequest.getExperience()))
                .andExpect(jsonPath("$.birthday").value(createPlayerRequest.getBirthday()))
                .andExpect(jsonPath("$.profession").value(createPlayerRequest.getProfession()))
                .andExpect(jsonPath("$.race").value(createPlayerRequest.getRace()))
                .andExpect(jsonPath("$.title").value(createPlayerRequest.getTitle()));
    }
    @Test
    void getPlayerByIdMvc() throws Exception {
        Long id = 1L;
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
                .id(1L)
                .build();


        mockMvc.perform(
                        get(GET_PLAYER_URL)
                                .content(objectMapper.writeValueAsString(id))
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(playerDto.getName()))
                .andExpect(jsonPath("$.banned").value(playerDto.getBanned()))
                .andExpect(jsonPath("$.experience").value(playerDto.getExperience()))
                .andExpect(jsonPath("$.birthday").value(playerDto.getBirthday()))
                .andExpect(jsonPath("$.profession").value(playerDto.getProfession()))
                .andExpect(jsonPath("$.race").value(playerDto.getRace()))
                .andExpect(jsonPath("$.title").value(playerDto.getTitle()));
    }
        @Test
        void updatePlayerMvc() throws Exception {
            Long id = 2L;
            UpdatePlayerRequest updatePlayerRequest = UpdatePlayerRequest.builder()
                    .name("name")
                    .birthday(new Date(1, 1, 1))
                    .race(Race.DWARF)
                    .title("title")
                    .banned(false)
                    .experience(11)
                    .profession(Profession.ROGUE)
                    .id(2L)
                    .build();


            mockMvc.perform(
                            post(UPDATE_PLAYER_URL)
                                    .content(objectMapper.writeValueAsString(id))
                                    .content(objectMapper.writeValueAsString(updatePlayerRequest))
                                    .header("Content-Type", "application/json")
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").isNumber())
                    .andExpect(jsonPath("$.name").value(updatePlayerRequest.getName()))
                    .andExpect(jsonPath("$.banned").value(updatePlayerRequest.getBanned()))
                    .andExpect(jsonPath("$.experience").value(updatePlayerRequest.getExperience()))
                    .andExpect(jsonPath("$.birthday").value(updatePlayerRequest.getBirthday()))
                    .andExpect(jsonPath("$.profession").value(updatePlayerRequest.getProfession()))
                    .andExpect(jsonPath("$.race").value(updatePlayerRequest.getRace()))
                    .andExpect(jsonPath("$.title").value(updatePlayerRequest.getTitle()));
        }
            @Test
            void getFilteredPlayersMvc() throws Exception {


                String name = "name";
                String title = "title";
                Race race = Race.DWARF;
                Profession profession = Profession.ROGUE;
                Date before = new Date(1, 1, 1);
                Date after = new Date(1, 1, 99);
                PlayerOrder order = PlayerOrder.ID;
                Long minExperience = 1L;
                Long maxExperience = 100L;
                Integer minLevel = 1;
                Integer maxLevel = 100;
                Boolean banned = true;
                Integer pageNumber = 1;
                Integer pageSize = 20;


                mockMvc.perform(
                                get(GET_FILTERED_PLAYER_URL)
                                        .param(objectMapper.writeValueAsString(title))
                                        .param(objectMapper.writeValueAsString(name))
                                        .param(objectMapper.writeValueAsString(race))
                                        .param(objectMapper.writeValueAsString(profession))
                                        .param(objectMapper.writeValueAsString(before))
                                        .param(objectMapper.writeValueAsString(after))
                                        .param(objectMapper.writeValueAsString(order))
                                        .param(objectMapper.writeValueAsString(minExperience))
                                        .param(objectMapper.writeValueAsString(maxExperience))
                                        .param(objectMapper.writeValueAsString(minLevel))
                                        .param(objectMapper.writeValueAsString(maxLevel))
                                        .param(objectMapper.writeValueAsString(banned))
                                        .param(objectMapper.writeValueAsString(pageNumber))
                                        .param(objectMapper.writeValueAsString(pageSize))
                                        .header("Content-Type", "application/json")
                        )
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").isNumber())
                        .andExpect(jsonPath("$.name").value(name))
                        .andExpect(jsonPath("$.banned").value(banned))
//                        .andExpect(jsonPath("$.experience")
                                .andExpect(jsonPath("$.race").value(race))
                        .andExpect(jsonPath("$.profession").value(profession))
//                        .andExpect(jsonPath("$.birthday").value()
                        .andExpect(jsonPath("$.title").value(title));
//                        .andExpect(jsonPath("$.level").value());
            }
    @Test
    void getFilteredPlayersCountMvc() throws Exception {

        String name = "name";
        String title = "title";
        Race race = Race.DWARF;
        Profession profession = Profession.ROGUE;
        Date before = new Date(1, 1, 1);
        Date after = new Date(1, 1, 99);
        PlayerOrder order = PlayerOrder.ID;
        Long minExperience = 1L;
        Long maxExperience = 100L;
        Integer minLevel = 1;
        Integer maxLevel = 100;
        Boolean banned = true;
        Integer pageNumber = 1;
        Integer pageSize = 20;


        mockMvc.perform(
                        get(GET_FILTERED_PLAYER_URL)
                                .param(objectMapper.writeValueAsString(title))
                                .param(objectMapper.writeValueAsString(name))
                                .param(objectMapper.writeValueAsString(race))
                                .param(objectMapper.writeValueAsString(profession))
                                .param(objectMapper.writeValueAsString(before))
                                .param(objectMapper.writeValueAsString(after))
                                .param(objectMapper.writeValueAsString(order))
                                .param(objectMapper.writeValueAsString(minExperience))
                                .param(objectMapper.writeValueAsString(maxExperience))
                                .param(objectMapper.writeValueAsString(minLevel))
                                .param(objectMapper.writeValueAsString(maxLevel))
                                .param(objectMapper.writeValueAsString(banned))
                                .param(objectMapper.writeValueAsString(pageNumber))
                                .param(objectMapper.writeValueAsString(pageSize))
                                .header("Content-Type", "application/json")
                );
//                .andExpect;
    }
//    @Test
//    void createPlayer() {
//        CreatePlayerRequest createPlayerRequest = CreatePlayerRequest.builder()
//                .name("name")
//                .banned(true)
//                .experience(12)
//                .birthday(new Date(1,1, 1))
//                .profession(Profession.ROGUE)
//                .race(Race.DWARF)
//                .title("title")
//                .build();
//        CreatePlayerResponse createPlayerResponse = playerController.createPlayer(createPlayerRequest);
//
//        assertEquals(createPlayerRequest.getName(), createPlayerResponse.getName());
//        assertEquals(createPlayerRequest.getBanned(), createPlayerResponse.getBanned());
//        assertEquals(createPlayerRequest.getExperience(), createPlayerResponse.getExperience());
//        assertEquals(createPlayerRequest.getBirthday(), createPlayerResponse.getBirthday());
//        assertEquals(createPlayerRequest.getProfession(), createPlayerResponse.getProfession());
//        assertEquals(createPlayerRequest.getRace(), createPlayerResponse.getRace());
//        assertEquals(createPlayerRequest.getTitle(), createPlayerResponse.getTitle());
//    }


//    @Test
//    void getPlayerById() {
//        Long id = 1L;
//        PlayerDto playerDto = PlayerDto.builder()
//                .name("name")
//                .birthday(new Date(1,1, 1))
//                .race(Race.DWARF)
//                .title("title")
//                .level(1)
//                .banned(false)
//                .experience(11)
//                .profession(Profession.ROGUE)
//                .untilNextLevel(1)
//                .id(1L)
//                .build();
//        GetPlayerResponse getPlayerResponse = playerController.getPlayerById(id);
//        assertEquals(playerDto.getName(), getPlayerResponse.getName());
//        assertEquals(playerDto.getBanned(), getPlayerResponse.getBanned());
//        assertEquals(playerDto.getExperience(), getPlayerResponse.getExperience());
//        assertEquals(playerDto.getBirthday(), getPlayerResponse.getBirthday());
//        assertEquals(playerDto.getProfession().toString(), getPlayerResponse.getProfession().toString());
//        assertEquals(playerDto.getRace().toString(), getPlayerResponse.getRace().toString());
//        assertEquals(playerDto.getTitle(), getPlayerResponse.getTitle());
//        assertEquals(playerDto.getLevel(), getPlayerResponse.getLevel());
//        assertEquals(playerDto.getUntilNextLevel(), getPlayerResponse.getUntilNextLevel());
//        assertEquals(playerDto.getId(), getPlayerResponse.getId());
//    }

    @Test
    void deletePlayer() {
    }

//    @Test
//    void updatePlayer() {
//        Long id = 2L;
//        UpdatePlayerRequest updatePlayerRequest = UpdatePlayerRequest.builder()
//                .name("name")
//                .birthday(new Date(1,1, 1))
//                .race(Race.DWARF)
//                .title("title")
//                .banned(false)
//                .experience(11)
//                .profession(Profession.ROGUE)
//                .id(2L)
//                .build();
//        UpdatePlayerResponse updatePlayerResponse = playerController.updatePlayer(id, updatePlayerRequest);
//        assertEquals(updatePlayerResponse.getName(), updatePlayerRequest.getName());
//        assertEquals(updatePlayerResponse.getBanned(), updatePlayerRequest.getBanned());
//        assertEquals(updatePlayerResponse.getExperience(), updatePlayerRequest.getExperience());
//        assertEquals(updatePlayerResponse.getBirthday(), updatePlayerRequest.getBirthday());
//        assertEquals(updatePlayerResponse.getProfession().toString(), updatePlayerRequest.getProfession().toString());
//        assertEquals(updatePlayerResponse.getRace().toString(), updatePlayerRequest.getRace().toString());
//        assertEquals(updatePlayerResponse.getTitle(), updatePlayerRequest.getTitle());
//        assertEquals(updatePlayerResponse.getId(), updatePlayerRequest.getId());
//    }

    @Test
    void getFilteredPlayers() {
        String name = "name";
        String title = "title";
        Race race = Race.DWARF;
        Profession profession = Profession.ROGUE;
        Date before = new Date(1,1, 1);
        Date after = new Date(1,1, 99);
        PlayerOrder order = PlayerOrder.ID;
        Long minExperience = 1L;
        Long maxExperience = 100L;
        Integer minLevel = 1;
        Integer maxLevel = 100;
        Boolean banned = true;
        Integer pageNumber = 1;
        Integer pageSize = 20;

    }

    @Test
    void getFilteredPlayersCount() {
    }
}