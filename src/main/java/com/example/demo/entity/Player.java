package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Column(nullable = false)
    private Race race;

    @Column(nullable = false)
    private Profession profession;

    @Column
    private int experience;

    @Column
    private double level = (Math.sqrt(2500 + 200 * experience) - 50) / 100;

    @Column
    private double untilNextLevel = 50 * (level + 1) * (level + 2) - experience;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private Boolean banned;
}
