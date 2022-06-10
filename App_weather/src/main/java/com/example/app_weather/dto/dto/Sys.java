package com.example.app_weather.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sys{
    private int type;
    private String country;
    private long sunrise;
    private long sunset;
}
