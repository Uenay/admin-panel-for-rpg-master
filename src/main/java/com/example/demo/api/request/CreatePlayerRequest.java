package com.example.demo.api.request;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@SuperBuilder
public class CreatePlayerRequest extends BasePlayerRequest {
}
