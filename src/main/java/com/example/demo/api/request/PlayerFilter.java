package com.example.demo.api.request;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PlayerFilter {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Date before;
    private Date after;
    private PlayerOrder order;
    private Long minExperience;
    private Long maxExperience;
    private Integer minLevel;
    private Integer maxLevel;
    private Boolean banned;
    private Integer pageNumber;
    private Integer pageSize;
}
