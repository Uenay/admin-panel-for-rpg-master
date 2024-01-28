package com.example.demo.api.request;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
public class CreatePlayerRequest {
    private String name;
    private String title;
    private Race race;
    private Profession profession;
    private Date birthday;
    private Boolean banned;
public CreatePlayerRequest(){
}
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
                ", birthday=" + birthday +
                ", banned=" + banned +
                '}';
    }
}
