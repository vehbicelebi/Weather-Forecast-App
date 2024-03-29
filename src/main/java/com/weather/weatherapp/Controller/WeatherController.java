package com.weather.weatherapp.Controller;

import com.weather.weatherapp.Weather;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WeatherController {
    @GetMapping("/")
    public String showPage(Model model){
        Weather weather = new Weather();
        model.addAttribute("weather", weather);
        return "start-page";
    }
    @PostMapping("/processForm")
    public String processForm(@ModelAttribute("weather") Weather weather){
        return "dashboard";
    }
}
