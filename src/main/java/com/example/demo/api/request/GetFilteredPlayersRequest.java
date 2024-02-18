package com.example.demo.api.request;

import com.example.demo.entity.Profession;
import com.example.demo.entity.ProfessionEntity;
import com.example.demo.entity.Race;
import com.example.demo.entity.RaceEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Builder
public class GetFilteredPlayersRequest {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Long minExperience;
    private Long maxExperience;
    private Integer minLevel;
    private Integer maxLevel;
    private Boolean banned;
    private Integer pageNumber;
    private Integer pageSize;
}
