package com.example.app_weather.controller;

import com.example.app_weather.dto.UrlDto;
import com.example.app_weather.repository.UserRepository;
import com.example.app_weather.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class UrlCotroller {
  //  private final UrlService urlService;
  @GetMapping
  public ResponseEntity getAll() throws IOException {
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
      return ResponseEntity.ok().body("Ishlavoti jinni");
  }
}
