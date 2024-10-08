package com.example.spring.springbootfeignclient.controller;

import com.example.spring.springbootfeignclient.client.WeatherClient;
import com.example.spring.springbootfeignclient.dto.weather.WeatherResponse;
import com.example.spring.springbootfeignclient.service.WearherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WearherService wearherService;

    @GetMapping("/weather")
    public WeatherResponse getWeatherData() {
        return wearherService.getWeatherData();
    }
}
