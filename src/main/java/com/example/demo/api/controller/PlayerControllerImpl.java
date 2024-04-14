package com.example.demo.api.controller;

import com.example.demo.api.request.CreatePlayerRequest;
import com.example.demo.api.request.PlayerFilter;
import com.example.demo.api.request.UpdatePlayerRequest;
import com.example.demo.api.response.BaseResponse;
import com.example.demo.api.response.CreatePlayerResponse;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.api.response.NotFoundResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<BaseResponse> getPlayerById(int id) {
        PlayerDto playerDto = playerService.getPlayerById(id);

        BaseResponse baseResponse;
        HttpStatus httpStatus;
        if (playerDto == null) {
            baseResponse = NotFoundResponse.builder()
                    .message("Player with id = " + id + " was not found")
                    .build();
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            baseResponse = DtoMapper.convertToGetResponse(playerDto);
            httpStatus = HttpStatus.OK;
        }

        return new ResponseEntity<>(baseResponse, httpStatus);
    }

    public ResponseEntity<Void> deletePlayer(int id) {
        HttpStatus httpStatus;
        if (playerService.getPlayerById(id) == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            playerService.deletePlayer(id);
            httpStatus = HttpStatus.OK;
        }
        return ResponseEntity.status(httpStatus).build();
    }

    public ResponseEntity<BaseResponse> updatePlayer(int id, UpdatePlayerRequest updatePlayerRequest) {
        updatePlayerRequest.setId(id);
        BaseResponse baseResponse;
        HttpStatus httpStatus;
        if (playerService.getPlayerById(id) == null) {
            baseResponse = NotFoundResponse.builder()
                    .message("Player with id = " + id + " was not found")
                    .build();
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            PlayerDto playerDto = DtoMapper.convertToPlayerDto(updatePlayerRequest);
            PlayerDto updatedPlayer = playerService.updatePlayer(playerDto);
            baseResponse = DtoMapper.convertToUpdateResponse(updatedPlayer);
            httpStatus = HttpStatus.OK;
        }

        return new ResponseEntity<>(baseResponse, httpStatus);
    }

    public List<GetPlayerResponse> getFilteredPlayers(String name,
                                                      String title,
                                                      Race race,
                                                      Profession profession,
                                                      Long before,
                                                      Long after,
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
                .before(new Date(before))
                .after(new Date(after))
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
                                       Long before,
                                       Long after,
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
                .before(new Date(before))
                .after(new Date(after))
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
