package com.example.demo.api.controller;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.PlayerFilter;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.api.response.UpdatePlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayerControllerImpl implements PlayerController {
    private final PlayerService playerService;

    public CreatePlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
        PlayerDto playerDto = DtoMapper.convertToPlayerDto(createPlayerRequest);
        PlayerDto createdPlayer = playerService.createPlayer(playerDto);
        return DtoMapper.convertToCreateResponse(createdPlayer);
    }

    public GetPlayerResponse getPlayerById(Long id) {
        Player player = DtoMapper.convertToPlayer(playerService.getPlayerById(id));
        return DtoMapper.convertToGetResponse(player);
    }

    public void deletePlayer(Long id) {
        playerService.deletePlayer(id);
    }

    public UpdatePlayerResponse updatePlayer(Long id, UpdatePlayerRequest updatePlayerRequest) {
        updatePlayerRequest.setId(id);
        PlayerDto playerDto = DtoMapper.convertToPlayerDto(updatePlayerRequest);
        PlayerDto updatedPlayer = playerService.updatePlayer(playerDto);
        return DtoMapper.convertToUpdateResponse(updatedPlayer);
    }

    public List<GetPlayerResponse> getFilteredPlayers(Root<GetPlayerResponse> root, CriteriaBuilder cb, PlayerFilter playerFilter) {
        List<GetPlayerResponse> filteredPlayers = playerService.getFilteredPlayers(playerFilter);
        return ResponseEntity.ok(filteredPlayers).getBody();
    }
    public int getFilteredPlayersCount(PlayerFilter playerFilter){
        int filteredPlayers = playerService.getFilteredPlayersCount(playerFilter);
        return filteredPlayers;
    }
}
