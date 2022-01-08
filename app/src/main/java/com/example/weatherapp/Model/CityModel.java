package com.example.weatherapp.Model;

public class CityModel {
    String cityName;

    public CityModel(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
