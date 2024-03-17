package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    private int level = (int) (Math.sqrt(2500 + 200 * experience) - 50) / 100;

    @Column(name = "until_next_level")
    private int untilNextLevel = 50 * (level + 1) * (level + 2) - experience;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private Boolean banned;

}
