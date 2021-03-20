package com.weather.city.model;

public class WeatherResponse {
    String error;
    WeatherData weatherData;

    public WeatherResponse(WeatherData weatherData, String error) {
        this.error = error;
        this.weatherData = weatherData;
    }

    public String getError() {
        return error;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

}
