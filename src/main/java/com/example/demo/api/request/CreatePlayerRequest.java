package com.example.demo.api.request;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
public class CreatePlayerRequest {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private int experience;
    private int level;
    private int untilNextLevel;
    private Date birthday;
    private Boolean banned;

    public void setName(String name){
        this.name = name;
        }
    public void setTitle(String title){
    this.title = title;
    }
    public void setRace(Race race) {
        this.race = race;
    }
    public void setProfession(Profession profession) {
        this.profession = profession;
    }
    public void setExperience(int experience){
    experience = 0;
    }
    public void setLevel(int level){
        level = (int) (Math.sqrt(2500 + 200*experience)-50)/100;
    }
    public void setUntilNextLevel(int untilNextLevel){
        untilNextLevel = 50*(level+1)*(level+2)-experience;
    }
    public void setBirthday(Date birthday){
    this.birthday = birthday;
    }
    public void setBanned(Boolean banned){
    this.banned = banned;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public int getUntilNextLevel() {
        return untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    @Override
    public String toString() {
        return "CreatePlayerRequest{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", experience=" + experience +
                ", level=" + level +
                ", untilNextLevel=" + untilNextLevel +
                ", birthday=" + birthday +
                ", banned=" + banned +
                '}';
    }
}
