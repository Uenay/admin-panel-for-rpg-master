package com.example.demo.service;


import com.example.demo.api.request.PlayerFilter;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.entity.*;
import com.example.demo.dto.PlayerDto;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.ProfessionRepository;
import com.example.demo.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final ProfessionRepository professionRepository;
    private final RaceRepository raceRepository;

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player = DtoMapper.convertToPlayer(playerDto);
        Player savedPlayer = playerRepository.save(player);
        return DtoMapper.convertToPlayerDto(savedPlayer);
    }

    @Override
    public PlayerDto getPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElseThrow();
        return DtoMapper.convertToPlayerDto(player);
    }

    @Override
    public List<GetPlayerResponse> getPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(DtoMapper::convertToGetResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePlayer(Long id) {
        playerRepository.delete(playerRepository.findById(id).orElseThrow());
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto updatePlayerRequest) {
        Player player = playerRepository.findById(updatePlayerRequest.getId()).orElseThrow();
        if (updatePlayerRequest.getName() != null) {
            player.setName(updatePlayerRequest.getName());
        }
        if (updatePlayerRequest.getBanned() != null) {
            player.setBanned(updatePlayerRequest.getBanned());
        }
        if (updatePlayerRequest.getRace() != null) {
            player.setRace(raceRepository.findByName(updatePlayerRequest.getRace().toString()));
        }
        if (updatePlayerRequest.getBirthday() != null) {
            player.setBirthday(updatePlayerRequest.getBirthday());
        }
        if (updatePlayerRequest.getExperience() != 0) {
            player.setExperience(updatePlayerRequest.getExperience());
        }
        if (updatePlayerRequest.getProfession() != null) {
            player.setProfession(professionRepository.findByName(updatePlayerRequest.getProfession().toString()));
        }
        if (updatePlayerRequest.getTitle() != null) {
            player.setTitle(updatePlayerRequest.getTitle());
        }
        Player updatedPlayer = playerRepository.save(player);
        return DtoMapper.convertToPlayerDto(updatedPlayer);
    }

    @Override
    public List<GetPlayerResponse> getFilteredPlayers(PlayerFilter playerFilter) {
        /*
            1. просим у базы ровно столько сколько нужно через criteria api
            2. конвертируем ответ
         */
        return null;
    }

    @Override
    public int getFilteredPlayersCount(PlayerFilter playerFilter) {
        /*
            запрашиваем из базы одно число - количество таких игроков (тоже через criteria api)
         */
        return 0;
    }
}
