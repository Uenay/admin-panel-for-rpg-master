package com.example.demo.service;


import com.example.demo.api.request.PlayerFilter;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.entity.ProfessionEntity;
import com.example.demo.entity.RaceEntity;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.repository.PlayerFilterSpec;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.ProfessionRepository;
import com.example.demo.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        RaceEntity raceEntity = raceRepository.findByName(playerDto.getRace().name());
        player.setRace(raceEntity);

        ProfessionEntity profession = professionRepository.findByName(playerDto.getProfession().name());
        player.setProfession(profession);


        Player savedPlayer = playerRepository.save(player);
        return DtoMapper.convertToPlayerDto(savedPlayer);
    }

    @Override
    public PlayerDto getPlayerById(int id) {
        Player player = playerRepository.findById(id).orElse(null);
        return player == null ? null : DtoMapper.convertToPlayerDto(player);
    }

    @Override
    public void deletePlayer(int id) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player != null) {
            playerRepository.delete(player);
        }
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto updatePlayerRequest) {
        Player player = playerRepository.findById(updatePlayerRequest.getId()).orElse(null);
        if (player == null) {
            return null;
        }

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

        return DtoMapper.convertToPlayerDto(playerRepository.save(player));
    }

    @Override
    public List<PlayerDto> getFilteredPlayers(PlayerFilter playerFilter) {
        if (playerFilter.getPageNumber() == null || playerFilter.getPageSize() == null || playerFilter.getOrder() == null) {
            throw new RuntimeException("Данные не заполнены");
        }

        Pageable pageable = PageRequest.of(playerFilter.getPageNumber(), playerFilter.getPageSize(), Sort.by(playerFilter.getOrder().getFieldName()));
        Page<Player> page =  playerRepository.findAll(new PlayerFilterSpec(playerFilter), pageable);
        List<Player> playersList = page.getContent();
        return playersList.stream()
                .map(DtoMapper::convertToPlayerDto)
                .collect(Collectors.toList());
    }

    @Override
    public int getFilteredPlayersCount(PlayerFilter playerFilter) {
        /*
            запрашиваем из базы одно число - количество таких игроков (тоже через criteria api)
         */
        return (int) playerRepository.count(new PlayerFilterSpec(playerFilter));
    }
}
