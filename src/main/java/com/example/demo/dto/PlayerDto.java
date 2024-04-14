package com.example.demo.dto;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private Integer id;

    @Min(1)
    @Max(12)
    private String name;

    @Min(1)
    @Max(30)
    private String title;

    private Race race;

    private Profession profession;

    private int experience;

    private int level;

    private int untilNextLevel;

    private Date birthday;

    private Boolean banned;
}
