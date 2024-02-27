package com.example.demo.service;

import com.example.demo.api.request.PlayerFilter;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.dto.PlayerDto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.List;

public interface PlayerService {
    PlayerDto createPlayer(PlayerDto createPlayerRequest);

    PlayerDto updatePlayer(PlayerDto updatePlayerRequest);

    PlayerDto getPlayerById(Long id);


    void deletePlayer(Long id);

    List<GetPlayerResponse> getFilteredPlayers(Root<GetPlayerResponse> root, CriteriaBuilder cb, PlayerFilter playerFilter);

    int getFilteredPlayersCount(PlayerFilter playerFilter);
}

