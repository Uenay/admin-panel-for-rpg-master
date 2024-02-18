package com.example.demo.mapper;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
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
    }

    @Test
    void convertToCreateResponse() {
    }

    @Test
    void convertToUpdateResponse() {
    }

    @Test
    void convertToGetResponse() {
    }

    @Test
    void convertToPlayer() {
    }

    @Test
    void testConvertToPlayerDto1() {
    }
}