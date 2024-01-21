package com.example.demo.service;



import com.example.demo.entity.Player;
import com.example.demo.dto.PlayerDto;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Override
    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player = DtoMapper.converToPlayer(playerDto);
        Player savedPlayer = playerRepository.save(player);
        return DtoMapper.converToPlayerDto(savedPlayer);
//        Player player = new Player();
//        player.setName(createPlayerRequest.getName());
//        player.setTitle(createPlayerRequest.getTitle());
//        player.setLevel(createPlayerRequest.getLevel());
//        player.setBirthday(createPlayerRequest.getBirthday());
//        player.setRace(createPlayerRequest.getRace());
//        player.setProfession(createPlayerRequest.getProfession());
//        player.setExperience(createPlayerRequest.getExperience());
//        player.setBanned(createPlayerRequest.getBanned());
//        player.setUntilNextLevel(createPlayerRequest.getUntilNextLevel());
//        Player createdPlayer = playerRepository.save(player);
//        return convertEntityToDto(createdPlayer);
    }

//    private PlayerDto convertEntityToDto(PlayerDto player) {
////        PlayerDto playerDto = new PlayerDto();
////        playerDto.setId(player.getId());
////        playerDto.setName(player.getName());
////        playerDto.setTitle(player.getTitle());
////        playerDto.setLevel(player.getLevel());
////        playerDto.setBirthday(player.getBirthday());
////        playerDto.setRace(player.getRace());
////        playerDto.setProfession(player.getProfession());
////        playerDto.setExperience(player.getExperience());
////        playerDto.setBanned(player.getBanned());
////        playerDto.setUntilNextLevel(player.getUntilNextLevel());
////        return playerDto;
//    }

    @Override
    public PlayerDto updatePlayer(PlayerDto updatePlayerRequest) {
//        Player player = playerRepository.findById(updatePlayerRequest.getId()).orElseThrow();
        return null;
    }
}
