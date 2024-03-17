package com.example.demo.api.controller;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.ProfessionEntity;
import com.example.demo.entity.Race;
import com.example.demo.entity.RaceEntity;
import com.example.demo.filter.PlayerOrder;
import com.example.demo.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

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


    private static final String CREATE_PLAYER_URL = "/rest/players";
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
                .andExpect(jsonPath("$.birthday").value(createPlayerRequest.getBirthday().toString()))
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
                Player player1 = new Player();
                player1.setName("name1");
                player1.setBirthday(new Date(1, 1, 1));
                RaceEntity race1 = new RaceEntity();
                race1.setName(Race.DWARF.toString());
                player1.setRace(race1);
                player1.setTitle("title1");
                player1.setLevel(1);
                player1.setBanned(false);
                player1.setExperience(11);
                ProfessionEntity profession1 = new ProfessionEntity();
                profession1.setName(Profession.ROGUE.toString());
                player1.setProfession(profession1);
                player1.setUntilNextLevel(1);
                player1.setId(1);

                Player player2 = new Player();
                player2.setName("name2");
                player2.setBirthday(new Date(1, 1, 1));
                RaceEntity race2 = new RaceEntity();
                race2.setName(Race.DWARF.toString());
                player2.setRace(race2);
                player2.setTitle("title2");
                player2.setLevel(2);
                player2.setBanned(false);
                player2.setExperience(30);
                ProfessionEntity profession2 = new ProfessionEntity();
                profession2.setName(Profession.ROGUE.toString());
                player2.setProfession(profession2);
                player2.setUntilNextLevel(1);
                player2.setId(2);

                Player player3 = new Player();
                player3.setName("name3");
                player3.setBirthday(new Date(1, 1, 1));
                RaceEntity race3 = new RaceEntity();
                race3.setName(Race.DWARF.toString());
                player3.setRace(race3);
                player3.setTitle("title3");
                player3.setLevel(3);
                player3.setBanned(false);
                player3.setExperience(50);
                ProfessionEntity profession3 = new ProfessionEntity();
                profession3.setName(Profession.ROGUE.toString());
                player3.setProfession(profession3);
                player3.setUntilNextLevel(1);
                player3.setId(3);
                // сначала добавить 3-5 игроков, а потом их фильтрами искать

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
                                        //.param("title", "kek")
                                        .param("title", title)
                                        .param("name", name)
                                        .param("race", String.valueOf(race))
                                        .param("profession", String.valueOf(profession))
                                        .param("before", String.valueOf(before))
                                        .param("after", String.valueOf(after))
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
                        .andExpect(jsonPath("$.id").isNumber())
                        .andExpect(jsonPath("$.name").value(name))
                        .andExpect(jsonPath("$.banned").value(banned))
//                        .andExpect(jsonPath("$.experience").value()
                                .andExpect(jsonPath("$.race").value(race))
                        .andExpect(jsonPath("$.profession").value(profession))
//                        .andExpect(jsonPath("$.birthday").value()
                        .andExpect(jsonPath("$.title").value(title));
//                        .andExpect(jsonPath("$.level").value());
            }
    @Test
    void getFilteredPlayersCountMvc() throws Exception {
        Player player1 = new Player();
        player1.setName("name1");
        player1.setBirthday(new Date(1, 1, 4));
        RaceEntity race1 = new RaceEntity();
        race1.setName(Race.DWARF.toString());
        player1.setRace(race1);
        player1.setTitle("title1");
        player1.setLevel(1);
        player1.setBanned(true);
        player1.setExperience(11);
        ProfessionEntity profession1 = new ProfessionEntity();
        profession1.setName(Profession.ROGUE.toString());
        player1.setProfession(profession1);
        player1.setUntilNextLevel(1);
        player1.setId(1);

        Player player2 = new Player();
        player2.setName("name2");
        player2.setBirthday(new Date(1, 1, 3));
        RaceEntity race2 = new RaceEntity();
        race2.setName(Race.DWARF.toString());
        player2.setRace(race2);
        player2.setTitle("title2");
        player2.setLevel(2);
        player2.setBanned(false);
        player2.setExperience(30);
        ProfessionEntity profession2 = new ProfessionEntity();
        profession2.setName(Profession.ROGUE.toString());
        player2.setProfession(profession2);
        player2.setUntilNextLevel(1);
        player2.setId(2);

        Player player3 = new Player();
        player3.setName("name3");
        player3.setBirthday(new Date(1, 1, 2));
        RaceEntity race3 = new RaceEntity();
        race3.setName(Race.DWARF.toString());
        player3.setRace(race3);
        player3.setTitle("title3");
        player3.setLevel(3);
        player3.setBanned(false);
        player3.setExperience(50);
        ProfessionEntity profession3 = new ProfessionEntity();
        profession3.setName(Profession.ROGUE.toString());
        player3.setProfession(profession3);
        player3.setUntilNextLevel(1);
        player3.setId(3);

        String name = "name";
        String title = "title";
        Race race = Race.DWARF;
        Profession profession = Profession.ROGUE;
        Date before = new Date(1, 1, 1);
        Date after = new Date(1, 1, 99);
        Long minExperience = 1L;
        Long maxExperience = 100L;
        Integer minLevel = 1;
        Integer maxLevel = 100;
        Boolean banned = true;



        mockMvc.perform(
                        get(GET_FILTERED_PLAYER_URL)
                                .param("title", title)
                                .param("name", name)
                                .param("race", String.valueOf(race))
                                .param("profession", String.valueOf(profession))
                                .param("before", String.valueOf(before))
                                .param("after", String.valueOf(after))
                                .param("minExperience", String.valueOf(minExperience))
                                .param("maxExperience", String.valueOf(maxExperience))
                                .param("minLevel", String.valueOf(minLevel))
                                .param("maxLevel", String.valueOf(maxLevel))
                                .param("banned", String.valueOf(banned))
                                .header("Content-Type", "application/json")
                                .header("Content-Type", "application/json")
                )
                .andExpect(status().isOk())
                        .andExpect(content().string("1"));
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