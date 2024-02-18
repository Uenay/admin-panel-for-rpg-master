package com.example.demo.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UpdatePlayerRequest extends BasePlayerRequest {
    private Long id;
    private Integer experience;
}
