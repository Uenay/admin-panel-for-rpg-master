package com.example.demo.api.controller;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MillisToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        return new Date(Long.parseLong(source));
    }
}
