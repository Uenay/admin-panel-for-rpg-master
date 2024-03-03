package com.example.demo.service;


import com.example.demo.api.request.GetFilteredPlayersRequest;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.entity.*;
import com.example.demo.dto.PlayerDto;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.ProfessionRepository;
import com.example.demo.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        player.setRace(raceRepository.findByName(playerDto.getRace().name()));
        player.setProfession(professionRepository.findByName(playerDto.getProfession().name()));
        Player savedPlayer = playerRepository.save(player);

        RaceEntity raceEntity = new RaceEntity();
        raceEntity.setName("MYNAME");
        raceRepository.save(raceEntity);

        RaceEntity raceEntity1 = new RaceEntity();
        raceEntity1.setName("MYNAME2341");
        raceRepository.save(raceEntity1);

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
    public List<GetPlayerResponse> getFilteredPlayers(GetFilteredPlayersRequest getFilteredPlayersRequest) {
        List<Player> filteredPlayers = new ArrayList<>();
        List<Player> players = playerRepository.findAll();
        for (Player player : players) {
            if ((getFilteredPlayersRequest.getName() == null || player.getName().contains(getFilteredPlayersRequest.getName()))
                    && (getFilteredPlayersRequest.getTitle() == null || player.getTitle().contains(getFilteredPlayersRequest.getTitle()))
                    && (getFilteredPlayersRequest.getRace() == null || player.getRace().getId() == raceRepository.findByName(getFilteredPlayersRequest.getRace().toString()).getId())
                    && (getFilteredPlayersRequest.getProfession() == null || player.getProfession().getId() == professionRepository.findByName(getFilteredPlayersRequest.getProfession().toString()).getId())
                    && (getFilteredPlayersRequest.getMinExperience() == null || player.getExperience() >= getFilteredPlayersRequest.getMinExperience())
                    && (getFilteredPlayersRequest.getMaxExperience() == null || player.getExperience() <= getFilteredPlayersRequest.getMaxExperience())
                    && (getFilteredPlayersRequest.getMinLevel() == null || player.getLevel() >= getFilteredPlayersRequest.getMinLevel())
                    && (getFilteredPlayersRequest.getMaxLevel() == null || player.getLevel() <= getFilteredPlayersRequest.getMaxLevel())
                    && (getFilteredPlayersRequest.getBanned() == null || player.getBanned() == getFilteredPlayersRequest.getBanned())) {
                filteredPlayers.add(player);
            }
        }
        return filteredPlayers.stream()
                .map(DtoMapper::convertToGetResponse)
                .collect(Collectors.toList());
    }
    @Override
    public int getFilteredPlayersCount(GetFilteredPlayersRequest getFilteredPlayersRequest) {
        List<Player> players = playerRepository.findAll();
        int filteredPlayers = 0;
        for (Player player : players) {
            if ((getFilteredPlayersRequest.getName() == null || player.getName().contains(getFilteredPlayersRequest.getName()))
                    && (getFilteredPlayersRequest.getTitle() == null || player.getTitle().contains(getFilteredPlayersRequest.getTitle()))
                    && (getFilteredPlayersRequest.getRace() == null || player.getRace().getId() == raceRepository.findByName(getFilteredPlayersRequest.getRace().toString()).getId())
                    && (getFilteredPlayersRequest.getProfession() == null || player.getProfession().getId() == professionRepository.findByName(getFilteredPlayersRequest.getProfession().toString()).getId())
                    && (getFilteredPlayersRequest.getMinExperience() == null || player.getExperience() >= getFilteredPlayersRequest.getMinExperience())
                    && (getFilteredPlayersRequest.getMaxExperience() == null || player.getExperience() <= getFilteredPlayersRequest.getMaxExperience())
                    && (getFilteredPlayersRequest.getMinLevel() == null || player.getLevel() >= getFilteredPlayersRequest.getMinLevel())
                    && (getFilteredPlayersRequest.getMaxLevel() == null || player.getLevel() <= getFilteredPlayersRequest.getMaxLevel())
                    && (getFilteredPlayersRequest.getBanned() == null || player.getBanned() == getFilteredPlayersRequest.getBanned())) {
                filteredPlayers++;
            }
        }
        return filteredPlayers;
    }
}
