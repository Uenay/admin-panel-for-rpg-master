package com.example.demo.api.request;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@SuperBuilder
@Data
@NoArgsConstructor
public abstract class BasePlayerRequest {
    @Min(1)
    @Max(12)
    private String name;
    @Min(1)
    @Max(30)
    private String title;
    @NotBlank
    private Race race;
    @NotBlank
    private Profession profession;
    @NotBlank
    private Date birthday;
    private Boolean banned;
    @Min(1)
    @Max(10000000)
    private Integer experience;
}
