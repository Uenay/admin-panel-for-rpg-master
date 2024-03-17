package com.example.demo.api.controller;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.PlayerFilter;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.api.response.UpdatePlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

    public GetPlayerResponse getPlayerById(int id) {
        PlayerDto playerDto = playerService.getPlayerById(id);
        return DtoMapper.convertToGetResponse(playerDto);
    }

    public void deletePlayer(int id) {
        playerService.deletePlayer(id);
    }

    public UpdatePlayerResponse updatePlayer(int id, UpdatePlayerRequest updatePlayerRequest) {
        updatePlayerRequest.setId(id);
        PlayerDto playerDto = DtoMapper.convertToPlayerDto(updatePlayerRequest);
        PlayerDto updatedPlayer = playerService.updatePlayer(playerDto);
        return DtoMapper.convertToUpdateResponse(updatedPlayer);
    }

    public List<GetPlayerResponse> getFilteredPlayers(String name,
                                                      String title,
                                                      Race race,
                                                      Profession profession,
                                                      Date before,
                                                      Date after,
                                                      PlayerOrder order,
                                                      Long minExperience,
                                                      Long maxExperience,
                                                      Integer minLevel,
                                                      Integer maxLevel,
                                                      Boolean banned,
                                                      Integer pageNumber,
                                                      Integer pageSize) {

         PlayerFilter playerFilter = PlayerFilter.builder()
                .name(name)
                .title(title)
                .race(race)
                .profession(profession)
                .before(before)
                .after(after)
                .order(order)
                .minExperience(minExperience)
                .maxExperience(maxExperience)
                .minLevel(minLevel)
                .maxLevel(maxLevel)
                .banned(banned)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

        List<PlayerDto> filteredPlayers = playerService.getFilteredPlayers(playerFilter);

        return ResponseEntity.ok(DtoMapper.convertToGetResponse(filteredPlayers)).getBody();
    }

    public int getFilteredPlayersCount(String name,
                                       String title,
                                       Race race,
                                       Profession profession,
                                       Date before,
                                       Date after,
                                       Long minExperience,
                                       Long maxExperience,
                                       Integer minLevel,
                                       Integer maxLevel,
                                       Boolean banned) {
        PlayerFilter playerFilter = PlayerFilter.builder()
                .name(name)
                .title(title)
                .race(race)
                .profession(profession)
                .before(before)
                .after(after)
                .minExperience(minExperience)
                .maxExperience(maxExperience)
                .minLevel(minLevel)
                .maxLevel(maxLevel)
                .banned(banned)
                .build();
        int filteredPlayers = playerService.getFilteredPlayersCount(playerFilter);
        return filteredPlayers;
    }
}
