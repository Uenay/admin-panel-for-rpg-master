package com.example.demo.service;

import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.repository.PlayerRepository;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerServiceImplTest {

    private PlayerRepository playerRepository;

    @Test
    void createPlayer() {
        PlayerDto playerDto = PlayerDto.builder()
                .name("name")
                .birthday(new Date())
                .race(Race.DWARF)
                .title("title")
                .banned(false)
                .experience(11)
                .profession(Profession.ROGUE)
                .build();
        Player player = DtoMapper.convertToPlayer(playerDto);
        Player savedPlayer = playerRepository.save(player);
        assertEquals(playerDto.getName(), savedPlayer.getName());
        assertEquals(playerDto.getBanned(), savedPlayer.getBanned());
        assertEquals(playerDto.getExperience(), savedPlayer.getExperience());
        assertEquals(playerDto.getBirthday(), savedPlayer.getBirthday());
        assertEquals(playerDto.getProfession().toString(), savedPlayer.getProfession().toString());
        assertEquals(playerDto.getRace().toString(), savedPlayer.getRace().toString());
        assertEquals(playerDto.getTitle(), savedPlayer.getTitle());
        assertEquals(playerDto.getLevel(), savedPlayer.getLevel());
        assertEquals(playerDto.getUntilNextLevel(), savedPlayer.getUntilNextLevel());
        assertEquals(playerDto.getId(), savedPlayer.getId());
    }

    @Test
    void getPlayerById() {
        int id = 2;
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
        Player actualPlayer = playerRepository.findById(id).orElseThrow();
        assertEquals(playerDto.getName(), actualPlayer.getName());
        assertEquals(playerDto.getBanned(), actualPlayer.getBanned());
        assertEquals(playerDto.getExperience(), actualPlayer.getExperience());
        assertEquals(playerDto.getBirthday(), actualPlayer.getBirthday());
        assertEquals(playerDto.getProfession().toString(), actualPlayer.getProfession().toString());
        assertEquals(playerDto.getRace().toString(), actualPlayer.getRace().toString());
        assertEquals(playerDto.getTitle(), actualPlayer.getTitle());
        assertEquals(playerDto.getLevel(), actualPlayer.getLevel());
        assertEquals(playerDto.getUntilNextLevel(), actualPlayer.getUntilNextLevel());
        assertEquals(playerDto.getId(), actualPlayer.getId());
    }

    @Test
    void deletePlayer() {
        int id = 2;
        PlayerDto playerDto = PlayerDto.builder()
                .id(2)
                .build();
        Player actualPlayer = playerRepository.findById(id).orElseThrow();
        assertEquals(playerDto.getId(), actualPlayer.getId());
    }

    @Test
    void updatePlayer() {
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
        Player player = DtoMapper.convertToPlayer(playerDto);
        Player updatedPlayer = playerRepository.save(player);
        assertEquals(playerDto.getName(), updatedPlayer.getName());
        assertEquals(playerDto.getBanned(), updatedPlayer.getBanned());
        assertEquals(playerDto.getExperience(), updatedPlayer.getExperience());
        assertEquals(playerDto.getBirthday(), updatedPlayer.getBirthday());
        assertEquals(playerDto.getProfession().toString(), updatedPlayer.getProfession().toString());
        assertEquals(playerDto.getRace().toString(), updatedPlayer.getRace().toString());
        assertEquals(playerDto.getTitle(), updatedPlayer.getTitle());
        assertEquals(playerDto.getLevel(), updatedPlayer.getLevel());
        assertEquals(playerDto.getUntilNextLevel(), updatedPlayer.getUntilNextLevel());
        assertEquals(playerDto.getId(), updatedPlayer.getId());
    }

    @Test
    void getFilteredPlayers() {
    }

    @Test
    void getFilteredPlayersCount() {
    }
}