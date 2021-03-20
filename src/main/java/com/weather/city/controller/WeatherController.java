package com.weather.city.controller;

import com.weather.city.model.WeatherResponse;
import com.weather.city.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


import static java.util.Collections.emptyList;

@RestController
@CrossOrigin
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/city")
    public ResponseEntity<List<String>> fetchCityName(@RequestParam("cityPrefix") String cityPrefix) {
        if (cityPrefix.isEmpty()) {
            return new ResponseEntity(emptyList(), HttpStatus.OK);
        }

        WeatherResponse response = weatherService.fetchWeatherData();

        if(response.getError()!=null){
            return new ResponseEntity(emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response.getWeatherData()
                .getList()
                .stream()
                .map(city -> city.getName())
                .filter(cityName -> cityName.toUpperCase().startsWith(cityPrefix.toUpperCase()))
                .collect(Collectors.toList()), HttpStatus.OK);

    }
}
