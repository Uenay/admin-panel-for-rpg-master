package com.example.demo;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
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
    private int level;
    @Column
    private int untilNextLevel;
    @Column(nullable = false)
    private Date birthday;
    @Column(nullable = false)
    private Boolean banned;
}
