package com.example.demo.service;


import com.example.demo.api.request.PlayerFilter;
import com.example.demo.api.response.GetPlayerResponse;
import com.example.demo.dto.PlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.ProfessionRepository;
import com.example.demo.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
        public List<GetPlayerResponse> getFilteredPlayers(Root<GetPlayerResponse> root, CriteriaBuilder cb, PlayerFilter playerFilter) {
            CriteriaQuery<GetPlayerResponse> query = cb.createQuery(GetPlayerResponse.class);
            List<Predicate> predicates = new ArrayList<>();

            if (playerFilter.getName() != null) {
                predicates.add(cb.like(root.get("name"), "%" + playerFilter.getName() + "%"));
            }
            if (playerFilter.getTitle() != null) {
                predicates.add(cb.like(root.get("title"), "%" + playerFilter.getTitle() + "%"));
            }

            if (playerFilter.getRace() != null) {
                predicates.add(cb.equal(root.get("race"), playerFilter.getRace()));
            }

            if (playerFilter.getProfession() != null) {
                predicates.add(cb.equal(root.get("profession"), playerFilter.getProfession()));
            }

            if (playerFilter.getMinExperience() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("experience"), playerFilter.getMinExperience()));
            }

            if (playerFilter.getMaxExperience() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("experience"), playerFilter.getMaxExperience()));
            }

            if (playerFilter.getMinLevel() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("level"), playerFilter.getMinLevel()));
            }

            if (playerFilter.getMaxLevel() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("level"), playerFilter.getMaxLevel()));
            }

            if (playerFilter.getBanned() != null) {
                predicates.add(cb.equal(root.get("banned"), playerFilter.getBanned()));
            }

            query.select(root).where(predicates.toArray(new Predicate[0]));
            List<GetPlayerResponse> filteredPlayers = query.getResultList();

    }

    @Override
    public int getFilteredPlayersCount(PlayerFilter playerFilter) {
        /*
            запрашиваем из базы одно число - количество таких игроков (тоже через criteria api)
         */
        return 0;
    }
}
