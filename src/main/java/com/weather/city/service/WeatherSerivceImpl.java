package com.weather.city.service;

import com.weather.city.model.WeatherData;
import com.weather.city.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherSerivceImpl implements WeatherService{

    @Autowired
    Environment environment;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public WeatherResponse fetchWeatherData() {
        String url= environment.getProperty("map-api-url");
        WeatherData weatherData = null;
        try {
            weatherData = restTemplate.getForObject(url, WeatherData.class);
        }catch(Exception e){
            return new WeatherResponse(null , e.getMessage());
        }
        return new WeatherResponse(weatherData, null);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
