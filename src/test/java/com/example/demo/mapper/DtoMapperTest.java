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
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoMapperTest {

    @Test
    void convertToPlayerDto() {
        CreatePlayerRequest createPlayerRequest = CreatePlayerRequest.builder()
                .name("name")
                .banned(true)
                .experience(12)
                .birthday(new Date())
                .profession(Profession.ROGUE)
                .race(Race.DWARF)
                .title("title")
                .build();

        PlayerDto actualPlayerDto = DtoMapper.convertToPlayerDto(createPlayerRequest);

        assertEquals(createPlayerRequest.getName(), actualPlayerDto.getName());
        assertEquals(createPlayerRequest.getBanned(), actualPlayerDto.getBanned());
        assertEquals(createPlayerRequest.getExperience(), actualPlayerDto.getExperience());
        assertEquals(createPlayerRequest.getBirthday(), actualPlayerDto.getBirthday());
        assertEquals(createPlayerRequest.getProfession(), actualPlayerDto.getProfession());
        assertEquals(createPlayerRequest.getRace(), actualPlayerDto.getRace());
        assertEquals(createPlayerRequest.getTitle(), actualPlayerDto.getTitle());
    }

    @Test
    void testConvertToPlayerDto() {
        UpdatePlayerRequest updatePlayerRequest = UpdatePlayerRequest.builder()
                .name("name")
                .banned(true)
                .experience(12)
                .birthday(new Date())
                .profession(Profession.ROGUE)
                .race(Race.DWARF)
                .title("title")
                .build();
        PlayerDto actualPlayerDto = DtoMapper.convertToPlayerDto(updatePlayerRequest);
        assertEquals(updatePlayerRequest.getName(), actualPlayerDto.getName());
        assertEquals(updatePlayerRequest.getBanned(), actualPlayerDto.getBanned());
        assertEquals(updatePlayerRequest.getExperience(), actualPlayerDto.getExperience());
        assertEquals(updatePlayerRequest.getBirthday(), actualPlayerDto.getBirthday());
        assertEquals(updatePlayerRequest.getProfession(), actualPlayerDto.getProfession());
        assertEquals(updatePlayerRequest.getRace(), actualPlayerDto.getRace());
        assertEquals(updatePlayerRequest.getTitle(), actualPlayerDto.getTitle());
    }

    @Test
    void convertToCreateResponse() {
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
                .id(2)
                .build();
        CreatePlayerResponse actualCreatePlayerResponse = DtoMapper.convertToCreateResponse(playerDto);
        assertEquals(playerDto.getName(), actualCreatePlayerResponse.getName());
        assertEquals(playerDto.getBanned(), actualCreatePlayerResponse.getBanned());
        assertEquals(playerDto.getExperience(), actualCreatePlayerResponse.getExperience());
        assertEquals(playerDto.getBirthday(), actualCreatePlayerResponse.getBirthday());
        assertEquals(playerDto.getProfession(), actualCreatePlayerResponse.getProfession());
        assertEquals(playerDto.getRace(), actualCreatePlayerResponse.getRace());
        assertEquals(playerDto.getTitle(), actualCreatePlayerResponse.getTitle());
        assertEquals(playerDto.getLevel(), actualCreatePlayerResponse.getLevel());
        assertEquals(playerDto.getUntilNextLevel(), actualCreatePlayerResponse.getUntilNextLevel());
        assertEquals(playerDto.getId(), actualCreatePlayerResponse.getId());
    }

    @Test
    void convertToUpdateResponse() {
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
                .id(2)
                .build();
        UpdatePlayerResponse actualUpdatePlayerResponse = DtoMapper.convertToUpdateResponse(playerDto);
        assertEquals(playerDto.getName(), actualUpdatePlayerResponse.getName());
        assertEquals(playerDto.getBanned(), actualUpdatePlayerResponse.getBanned());
        assertEquals(playerDto.getExperience(), actualUpdatePlayerResponse.getExperience());
        assertEquals(playerDto.getBirthday(), actualUpdatePlayerResponse.getBirthday());
        assertEquals(playerDto.getProfession(), actualUpdatePlayerResponse.getProfession());
        assertEquals(playerDto.getRace(), actualUpdatePlayerResponse.getRace());
        assertEquals(playerDto.getTitle(), actualUpdatePlayerResponse.getTitle());
        assertEquals(playerDto.getLevel(), actualUpdatePlayerResponse.getLevel());
        assertEquals(playerDto.getUntilNextLevel(), actualUpdatePlayerResponse.getUntilNextLevel());
        assertEquals(playerDto.getId(), actualUpdatePlayerResponse.getId());
    }

    @Test
    void convertToGetResponse() {
        PlayerDto player = new PlayerDto();
        player.setName("name");
        player.setBirthday(new Date());
        player.setRace(Race.DWARF);
        player.setTitle("title");
        player.setLevel(1);
        player.setBanned(false);
        player.setExperience(11);
        player.setProfession(Profession.ROGUE);
        player.setUntilNextLevel(1);
        player.setId(2);
        GetPlayerResponse actualGetPlayerResponse = DtoMapper.convertToGetResponse(player);
        assertEquals(player.getName(), actualGetPlayerResponse.getName());
        assertEquals(player.getBanned(), actualGetPlayerResponse.getBanned());
        assertEquals(player.getExperience(), actualGetPlayerResponse.getExperience());
        assertEquals(player.getBirthday(), actualGetPlayerResponse.getBirthday());
        assertEquals(player.getProfession(), actualGetPlayerResponse.getProfession());
        assertEquals(player.getRace(), actualGetPlayerResponse.getRace());
        assertEquals(player.getTitle(), actualGetPlayerResponse.getTitle());
        assertEquals(player.getLevel(), actualGetPlayerResponse.getLevel());
        assertEquals(player.getUntilNextLevel(), actualGetPlayerResponse.getUntilNextLevel());
        assertEquals(player.getId(), actualGetPlayerResponse.getId());

    }

    @Test
    void convertToPlayer() {
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
                .id(2)
                .build();
        Player actualPlayer = DtoMapper.convertToPlayer(playerDto);
        assertEquals(playerDto.getName(), actualPlayer.getName());
        assertEquals(playerDto.getBanned(), actualPlayer.getBanned());
        assertEquals(playerDto.getExperience(), actualPlayer.getExperience());
        assertEquals(playerDto.getBirthday(), actualPlayer.getBirthday());
        assertEquals(playerDto.getProfession().toString(), actualPlayer.getProfession().getName());
        assertEquals(playerDto.getRace().toString(), actualPlayer.getRace().getName());
        assertEquals(playerDto.getTitle(), actualPlayer.getTitle());
        assertEquals(playerDto.getLevel(), actualPlayer.getLevel());
        assertEquals(playerDto.getUntilNextLevel(), actualPlayer.getUntilNextLevel());
        assertEquals(playerDto.getId(), actualPlayer.getId());

    }

    @Test
    void testConvertToPlayerDto1() {
        Player player = new Player();
        player.setName("name");
        player.setBirthday(new Date());
        RaceEntity race = new RaceEntity();
        race.setName(Race.DWARF.toString());
        player.setRace(race);
        player.setTitle("title");
        player.setLevel(1);
        player.setBanned(false);
        player.setExperience(11);
        ProfessionEntity profession = new ProfessionEntity();
        profession.setName(Profession.ROGUE.toString());
        player.setProfession(profession);
        player.setUntilNextLevel(1);
        player.setId(2);
        PlayerDto actualPlayerDto = DtoMapper.convertToPlayerDto(player);
        assertEquals(player.getName(), actualPlayerDto.getName());
        assertEquals(player.getBanned(), actualPlayerDto.getBanned());
        assertEquals(player.getExperience(), actualPlayerDto.getExperience());
        assertEquals(player.getBirthday(), actualPlayerDto.getBirthday());
        assertEquals(player.getProfession().getName(), actualPlayerDto.getProfession().toString());
        assertEquals(player.getRace().getName(), actualPlayerDto.getRace().toString());
        assertEquals(player.getTitle(), actualPlayerDto.getTitle());
        assertEquals(player.getLevel(), actualPlayerDto.getLevel());
        assertEquals(player.getUntilNextLevel(), actualPlayerDto.getUntilNextLevel());
        assertEquals(player.getId(), actualPlayerDto.getId());
    }
}