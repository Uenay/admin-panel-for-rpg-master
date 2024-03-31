package com.example.demo.api.controller;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import com.example.demo.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PlayerControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private MillisToDateConverter millisToDateConverter;


    private static final String CREATE_PLAYER_URL = "/rest/players";
    private static final String GET_PLAYER_URL = "/rest/players/{id}";
    private static final String UPDATE_PLAYER_URL = "/rest/players/{id}";
    private static final String GET_FILTERED_PLAYER_URL = "/rest/players";
    private static final String GET_FILTERED_PLAYER_COUNT_URL = "/rest/players/count";
    private static final String DELETE_PLAYER_URL = "/rest/players/{id}";

    @Test
    void createPlayerMvc() throws Exception {
        CreatePlayerRequest createPlayerRequest = CreatePlayerRequest.builder()
                .name("name")
                .banned(true)
                .experience(12)
                .birthday(new Date(122,1, 1))
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
                .andExpect(jsonPath("$.birthday").value(createPlayerRequest.getBirthday().getTime()))
                .andExpect(jsonPath("$.profession").value(createPlayerRequest.getProfession().name()))
                .andExpect(jsonPath("$.race").value(createPlayerRequest.getRace().name()))
                .andExpect(jsonPath("$.title").value(createPlayerRequest.getTitle()));
    }
    @Test
    void getPlayerByIdMvc() throws Exception {
        // добавить игрока, получить его айди и только после этого мы точно уверены что такого игрока мы найдем



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



        mockMvc.perform(
                        get(GET_PLAYER_URL, createdPlayer.getId())
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(playerDto.getName()))
                .andExpect(jsonPath("$.banned").value((playerDto.getBanned())))
                .andExpect(jsonPath("$.experience").value((playerDto.getExperience())))
                .andExpect(jsonPath("$.birthday").value((playerDto.getBirthday())))
                .andExpect(jsonPath("$.profession").value((playerDto.getProfession().name())))
                .andExpect(jsonPath("$.race").value((playerDto.getRace().name())))
                .andExpect(jsonPath("$.title").value((playerDto.getTitle())));
    }

    @Test
    void updatePlayerMvc() throws Exception {
    //сначала создаем, понимаем, какой есть айди и его обновляем
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
                .name("name1")
                .birthday(new Date(1, 1, 1))
                .race(Race.DWARF)
                .title("title1")
                .banned(false)
                .experience(12)
                .profession(Profession.ROGUE)
                .id(createdPlayer.getId())
                .build();


        mockMvc.perform(
                        post(UPDATE_PLAYER_URL, createdPlayer.getId())
                                .content(objectMapper.writeValueAsString(updatePlayerRequest))
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(updatePlayerRequest.getName()))
                .andExpect(jsonPath("$.banned").value(updatePlayerRequest.getBanned()))
                .andExpect(jsonPath("$.experience").value(updatePlayerRequest.getExperience()))
                .andExpect(jsonPath("$.birthday").value(updatePlayerRequest.getBirthday()))
                .andExpect(jsonPath("$.profession").value(updatePlayerRequest.getProfession().name()))
                .andExpect(jsonPath("$.race").value(updatePlayerRequest.getRace().name()))
                .andExpect(jsonPath("$.title").value(updatePlayerRequest.getTitle()));
    }

    @Test
    void getFilteredPlayersMvc() throws Exception {
        PlayerDto playerDto1 = PlayerDto.builder()
                .name("name1")
                .birthday(new Date(122, 1, 1))
                .race(Race.DWARF)
                .title("title1")
                .level(1)
                .banned(false)
                .experience(11)
                .profession(Profession.ROGUE)
                .untilNextLevel(1)
                .build();
        PlayerDto createdPlayer1 = playerService.createPlayer(playerDto1);

        PlayerDto playerDto2 = PlayerDto.builder()
                .name("name2")
                .birthday(new Date(123, 2, 1))
                .race(Race.DWARF)
                .title("title2")
                .level(2)
                .banned(false)
                .experience(22)
                .profession(Profession.ROGUE)
                .untilNextLevel(2)
                .build();
        PlayerDto createdPlayer2 = playerService.createPlayer(playerDto2);

        PlayerDto playerDto3 = PlayerDto.builder()
                .name("name3")
                .birthday(new Date(125, 3, 1))
                .race(Race.ORC)
                .title("title3")
                .level(3)
                .banned(false)
                .experience(3)
                .profession(Profession.ROGUE)
                .untilNextLevel(3)
                .build();
        PlayerDto createdPlayer3 = playerService.createPlayer(playerDto3);

        String name = "name";
        String title = "title";
        Race race = Race.DWARF;
        Profession profession = Profession.ROGUE;
        Date before = new Date(125, 1, 1);
        Date after = new Date(120, 1, 99);
        PlayerOrder order = PlayerOrder.ID;
        Long minExperience = 1L;
        Long maxExperience = 100L;
        Integer minLevel = 1;
        Integer maxLevel = 100;
        Boolean banned = false;
        Integer pageNumber = 0;
        Integer pageSize = 20;

        List<Integer> expectedIds = Arrays.asList(createdPlayer1.getId(), createdPlayer2.getId());
        List<String> expectedNames = Arrays.asList(createdPlayer1.getName(), createdPlayer2.getName());
        List<String> expectedTitles = Arrays.asList(createdPlayer1.getTitle(), createdPlayer2.getTitle());
        List<String> expectedRaces = Arrays.asList(createdPlayer1.getRace().name(), createdPlayer2.getRace().name());
        List<String> expectedProfession = Arrays.asList(createdPlayer1.getProfession().name(), createdPlayer2.getProfession().name());
        List<Integer> expectedExperiences = Arrays.asList(createdPlayer1.getExperience(), createdPlayer2.getExperience());
        List<Integer> expectedLevels = Arrays.asList(createdPlayer1.getLevel(), createdPlayer2.getLevel());
        List<Integer> expectedUntilNextLevels = Arrays.asList(createdPlayer1.getUntilNextLevel(), createdPlayer2.getUntilNextLevel());
        List<Long> expectedBirthdays = Arrays.asList(createdPlayer1.getBirthday().getTime(), createdPlayer2.getBirthday().getTime());
        List<Boolean> expectedBanned = Arrays.asList(createdPlayer1.getBanned(), createdPlayer2.getBanned());

        mockMvc.perform(
                        get(GET_FILTERED_PLAYER_URL)
                                .param("title", title)
                                .param("name", name)
                                .param("race", String.valueOf(race))
                                .param("profession", String.valueOf(profession))
                                .param("before", String.valueOf(before.getTime()))
                                .param("after", String.valueOf(after.getTime()))
                                .param("order", String.valueOf(order))
                                .param("minExperience", String.valueOf(minExperience))
                                .param("maxExperience", String.valueOf(maxExperience))
                                .param("minLevel", String.valueOf(minLevel))
                                .param("maxLevel", String.valueOf(maxLevel))
                                .param("banned", String.valueOf(banned))
                                .param("pageNumber", String.valueOf(pageNumber))
                                .param("pageSize", String.valueOf(pageSize))
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(expectedIds.toArray())))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder(expectedNames.toArray())))
                .andExpect(jsonPath("$[*].title", containsInAnyOrder(expectedTitles.toArray())))
                .andExpect(jsonPath("$[*].race", containsInAnyOrder(expectedRaces.toArray())))
                .andExpect(jsonPath("$[*].profession", containsInAnyOrder(expectedProfession.toArray())))
                .andExpect(jsonPath("$[*].experience", containsInAnyOrder(expectedExperiences.toArray())))
                .andExpect(jsonPath("$[*].level", containsInAnyOrder(expectedLevels.toArray())))
                .andExpect(jsonPath("$[*].untilNextLevel", containsInAnyOrder(expectedUntilNextLevels.toArray())))
                .andExpect(jsonPath("$[*].birthday", containsInAnyOrder(expectedBirthdays.toArray())))
                .andExpect(jsonPath("$[*].banned", containsInAnyOrder(expectedBanned.toArray())));
    }

    @Test
    void getFilteredPlayersCountMvc() throws Exception {
        PlayerDto playerDto1 = PlayerDto.builder()
                .name("countName1")
                .birthday(new Date(122, 1, 1))
                .race(Race.DWARF)
                .title("title1")
                .level(1)
                .banned(false)
                .experience(11)
                .profession(Profession.ROGUE)
                .untilNextLevel(1)
                .build();
        PlayerDto createdPlayer1 = playerService.createPlayer(playerDto1);

        PlayerDto playerDto2 = PlayerDto.builder()
                .name("countName2")
                .birthday(new Date(123, 2, 1))
                .race(Race.DWARF)
                .title("title2")
                .level(2)
                .banned(false)
                .experience(22)
                .profession(Profession.ROGUE)
                .untilNextLevel(2)
                .build();
        PlayerDto createdPlayer2 = playerService.createPlayer(playerDto2);

        PlayerDto playerDto3 = PlayerDto.builder()
                .name("countname3")
                .birthday(new Date(125, 3, 1))
                .race(Race.ORC)
                .title("title3")
                .level(3)
                .banned(false)
                .experience(3)
                .profession(Profession.ROGUE)
                .untilNextLevel(3)
                .build();
        PlayerDto createdPlayer3 = playerService.createPlayer(playerDto3);
        List<PlayerDto> Players = Arrays.asList(createdPlayer1, createdPlayer2, createdPlayer3);

        String name = "countName";
        String title = "title";
        Race race = Race.DWARF;
        Profession profession = Profession.ROGUE;
        Date before = new Date(125, 1, 1);
        Date after = new Date(120, 1, 1);
        Long minExperience = 1L;
        Long maxExperience = 100L;
        Integer minLevel = 1;
        Integer maxLevel = 100;
        Boolean banned = false;



        mockMvc.perform(
                        get(GET_FILTERED_PLAYER_COUNT_URL)
                                .param("title", title)
                                .param("name", name)
                                .param("race", String.valueOf(race))
                                .param("profession", String.valueOf(profession))
                                .param("before", String.valueOf(before.getTime()))
                                .param("after", String.valueOf(after.getTime()))
                                .param("minExperience", String.valueOf(minExperience))
                                .param("maxExperience", String.valueOf(maxExperience))
                                .param("minLevel", String.valueOf(minLevel))
                                .param("maxLevel", String.valueOf(maxLevel))
                                .param("banned", String.valueOf(banned))
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isOk())
                        .andExpect(content().string("2"));
    }


    @Test
    void deletePlayerMvc() throws Exception{
        PlayerDto playerDto1 = PlayerDto.builder()
                .name("name1")
                .birthday(new Date(122, 1, 1))
                .race(Race.DWARF)
                .title("title1")
                .level(1)
                .banned(false)
                .experience(11)
                .profession(Profession.ROGUE)
                .untilNextLevel(1)
                .build();

        PlayerDto createdPlayer = playerService.createPlayer(playerDto1);

        mockMvc.perform(
                        delete(DELETE_PLAYER_URL, createdPlayer.getId())
                                .header("Content-Type", "application/json")
        )
                .andExpect(status().isOk());
    }

}