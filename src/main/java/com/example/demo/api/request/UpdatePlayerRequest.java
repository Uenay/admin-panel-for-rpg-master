package com.example.demo.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@SuperBuilder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UpdatePlayerRequest extends BasePlayerRequest {
    private Integer id;
    @Min(1)
    @Max(10000000)
    private Integer experience;
}
