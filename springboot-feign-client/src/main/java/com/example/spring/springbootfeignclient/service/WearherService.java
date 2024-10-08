package com.example.spring.springbootfeignclient.service;

import com.example.spring.springbootfeignclient.client.WeatherClient;
import com.example.spring.springbootfeignclient.dto.weather.WeatherResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WearherService {
    private final WeatherClient weatherClient;
    private final ObjectMapper objectMapper;
    @Value("${feign-data.weather.api.key}")
    private String serviceKey;

    public WeatherResponse getWeatherData(){
        int numOfRows = 10;
        int pageNo = 1;
        String dataType = "JSON";
        String baseData = "20241008";
        String baseTime = "1400";
        int nx = 60;
        int ny = 127;

        try {
        String weatherData = weatherClient.getWeatherData(
                serviceKey,
                numOfRows,
                pageNo,
                dataType,
                baseData,
                baseTime,
                nx,
                ny
        );

            return objectMapper.readValue(weatherData, WeatherResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
