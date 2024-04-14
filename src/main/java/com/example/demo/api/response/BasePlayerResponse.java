package com.example.demo.api.response;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class BasePlayerResponse extends BaseResponse {
    private Integer id;
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private int experience;
    private int level;
    private int untilNextLevel;
    private Long birthday;
    private Boolean banned;
}
