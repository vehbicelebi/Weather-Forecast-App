package com.weather.weatherapp.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.weatherapp.Weather;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {
    @GetMapping("/")
    public String showPage(Model model){
        Weather weather = new Weather();
        model.addAttribute("weather", weather);
        return "start-page";
    }
    @PostMapping("/processForm")
    public String processForm(Weather weather){
        String url = "https://api.openweathermap.org/data/2.5/weather" + "?q=" + weather.getStadt() + "&appid=" + "f41eba3e7feef04c672a6da3044f62a0";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        try {
            // Verwendung der Jackson-Bibliothek, um die JSON-Antwort zu analysieren
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(result);
            // Extrahieren der Temperatur aus der JSON-Antwort
            int temperatureKelvin = root.path("main").path("temp").asInt();
            // Konvertieren der Temperatur von Kelvin nach Celsius
            int temperatureCelsius = (int) (temperatureKelvin - 273.15);
            // Setzen der Temperatur im Weather-Objekt
            weather.setGrad(String.valueOf(temperatureCelsius));

            String weatherDescription = root.path("weather").get(0).path("description").asText();
            weather.setDescription(weatherDescription);
        } catch (Exception e) {
            e.printStackTrace();
            return "start-page"; // Weiterleitung zur Fehlerseite
        }
        return "dashboard";
    }
}
