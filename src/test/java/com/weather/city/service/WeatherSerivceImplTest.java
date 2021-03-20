package com.weather.city.service;


import com.weather.city.model.WeatherData;
import com.weather.city.model.WeatherResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherSerivceImplTest {

    @Mock
    Environment environment;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    WeatherSerivceImpl weatherSerivceImpl;


    @Test
    public void whenFetchWeatherDataWithExceptions(){
        //given
        String url = "https://test/mapbox";
        when(environment.getProperty(any())).thenReturn(url);
        when(restTemplate.getForObject(url, WeatherData.class)).thenThrow(new RuntimeException("Exception thrown"));

        //when
        WeatherResponse response = weatherSerivceImpl.fetchWeatherData();


        //then
        Assert.assertEquals("Exception thrown", response.getError());
    }
}
