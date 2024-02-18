package com.example.demo.api.response;

import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.Date;
@ToString(callSuper = true)
@SuperBuilder
public class GetPlayerResponse extends BasePlayerResponse{

}
