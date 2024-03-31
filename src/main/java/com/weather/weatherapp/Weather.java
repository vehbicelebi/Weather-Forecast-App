package com.weather.weatherapp;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Weather {
    private String grad;

    private String stadt;
    private String description;

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
