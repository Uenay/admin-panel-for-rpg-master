package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private RaceEntity race;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id")
    private ProfessionEntity profession;

    @Column
    private int experience;

    @Column
    private double level = (Math.sqrt(2500 + 200 * experience) - 50) / 100;

    @Column(name = "untilnextlevel")
    private double untilNextLevel = 50 * (level + 1) * (level + 2) - experience;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private Boolean banned;

}
