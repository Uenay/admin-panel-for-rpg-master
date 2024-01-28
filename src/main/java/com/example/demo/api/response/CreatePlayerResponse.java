package com.example.demo.api.response;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Builder;

import java.util.Date;
@Builder
public class CreatePlayerResponse {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private int experience;
    private double level;
    private double untilNextLevel;
    private Date birthday;
    private Boolean banned;
}
