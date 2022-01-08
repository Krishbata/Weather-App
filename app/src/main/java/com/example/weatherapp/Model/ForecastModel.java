package com.example.weatherapp.Model;

public class ForecastModel {
    String forecastDay;
    String forecastIcon;
    String forecastMinTemp;
    String forecastMaxTemp;
    String forecastDate;

    public ForecastModel(String forecastDay, String forecastIcon, String forecastMinTemp, String forecastMaxTemp, String forecastDate) {
        this.forecastDay = forecastDay;
        this.forecastIcon = forecastIcon;
        this.forecastMinTemp = forecastMinTemp;
        this.forecastMaxTemp = forecastMaxTemp;
        this.forecastDate = forecastDate;
    }

    public ForecastModel() {
    }

    public String getForecastDay() {
        return forecastDay;
    }

    public void setForecastDay(String forecastDay) {
        this.forecastDay = forecastDay;
    }

    public String getForecastIcon() {
        return forecastIcon;
    }

    public void setForecastIcon(String forecastIcon) {
        this.forecastIcon = forecastIcon;
    }

    public String getForecastMinTemp() {
        return forecastMinTemp;
    }

    public void setForecastMinTemp(String forecastMinTemp) {
        this.forecastMinTemp = forecastMinTemp;
    }

    public String getForecastMaxTemp() {
        return forecastMaxTemp;
    }

    public void setForecastMaxTemp(String forecastMaxTemp) {
        this.forecastMaxTemp = forecastMaxTemp;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }
}
