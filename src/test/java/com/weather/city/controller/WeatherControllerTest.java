package com.weather.city.controller;


import com.weather.city.model.City;
import com.weather.city.model.WeatherData;
import com.weather.city.model.WeatherResponse;
import com.weather.city.service.WeatherService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {
    @Mock
    WeatherService weatherService;

    @InjectMocks
    WeatherController weatherController;

    @Test
    public void fetchCityName() {
        //Given
        WeatherResponse weatherResponse = initWeatherData();

        //when
        when(weatherService.fetchWeatherData()).thenReturn(weatherResponse);
        //then
        ResponseEntity<List<String>> response = weatherController.fetchCityName("london");

        Assert.assertEquals(1, response.getBody().size());

    }

    @Test
    public void fetchCityNameWithNoValues() {

        //Given
        WeatherResponse weatherResponse = initWeatherData();
        when(weatherService.fetchWeatherData()).thenReturn(weatherResponse);

        //when
        ResponseEntity<List<String>> response = weatherController.fetchCityName("1");

        //then
        Assert.assertEquals(0, response.getBody().size());

    }

    @Test
    public void fetchCityNameWithErrorResponse() {

        //Given
        WeatherResponse weatherResponse = new WeatherResponse(null, "Error while invoking Service");
        when(weatherService.fetchWeatherData()).thenReturn(weatherResponse);

        //when
        ResponseEntity<List<String>> response = weatherController.fetchCityName("1");

        //then
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

    private WeatherResponse initWeatherData() {

        WeatherData weatherData = new WeatherData();
        List<City> cityList = new ArrayList<>();
        City city = new City();
        city.setName("london");
        cityList.add(city);
        weatherData.setList(cityList);

        return new WeatherResponse(weatherData , null);
    }

}
