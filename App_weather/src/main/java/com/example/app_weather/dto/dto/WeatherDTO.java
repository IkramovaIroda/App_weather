package com.example.app_weather.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
    public class WeatherDTO {
//    @SerializedName("coord")
        private coord coord;
 //   @SerializedName("weather")
        private weather[] weather;
  //  @SerializedName("base")
        private String base;
   // @SerializedName("main")
        private Main main;
   // @SerializedName("visibility")
    private int visibility;
   // @SerializedName("wind")
    private Wind wind;
   // @SerializedName("clouds")
    private Clouds clouds;
   // @SerializedName("dt")
    private long dt;
//    @SerializedName("timezone")
    private long timezone;
   // @SerializedName("id")
    private long id;
   // @SerializedName("name")
    private String name;
   // @SerializedName("cod")
    private int cod;

    }
