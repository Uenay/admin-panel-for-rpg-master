package com.example.demo.api.request;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@SuperBuilder
@Data
@NoArgsConstructor
public abstract class BasePlayerRequest {
    @Size(min = 1, max = 12)
    private String name;
    @Size(min = 1, max = 30)
    private String title;
    @NotNull
    private Race race;
    @NotNull
    private Profession profession;
    @NotNull
    private Date birthday;
    private Boolean banned;
    @Min(1)
    @Max(10000000)
    private Integer experience;
}
