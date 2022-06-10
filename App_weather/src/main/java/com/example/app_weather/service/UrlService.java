package com.example.app_weather.service;

import com.example.app_weather.dto.ApiResponse;
import com.example.app_weather.dto.UrlDto;
import com.example.app_weather.dto.dto.WeatherDTO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;

@Service
@RequiredArgsConstructor
public class UrlService {
    public ResponseEntity<WeatherDTO> getWeather(UrlDto dto)throws IOException {
        String url1 = String.format( "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid=c1bfbf2e4b102a8e72480b3754321694" , dto.getLat(), dto.getLon());
        URL url = new URL(url1);
        URLConnection connection = url.openConnection();
        InputStream stream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null) {
            System.out.println(line);
            stringBuilder.append(line);
            line = bufferedReader.readLine();
        }
        Gson gson = new Gson();
        WeatherDTO weatherDTO = gson.fromJson(stringBuilder.toString() , WeatherDTO.class);

     return ResponseEntity.ok(weatherDTO);
    }
}
