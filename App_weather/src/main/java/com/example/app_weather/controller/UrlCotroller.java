package com.example.app_weather.controller;

import com.example.app_weather.dto.ApiResponse;
import com.example.app_weather.dto.UrlDto;
import com.example.app_weather.dto.dto.WeatherDTO;
import com.example.app_weather.repository.UserRepository;
import com.example.app_weather.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class UrlCotroller {
    private final UserRepository userRepository;
  //  private final UrlService urlService;
    private final UrlService urlService;
  @GetMapping("/get")
  public ApiResponse getAll() throws IOException {

        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=41.311081&lon=69.240562&appid=c1bfbf2e4b102a8e72480b3754321694");
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
      return ApiResponse.builder().message("molodes").success(true).obj(stringBuilder).build();
  }
    @PostMapping("/weather")
    public ResponseEntity<ResponseEntity<WeatherDTO>> GetWeather(@RequestBody UrlDto dto) throws IOException{
return ResponseEntity.ok(urlService.getWeather(dto));
    }
}
