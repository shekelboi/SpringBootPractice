package kevin.spring.practice.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;
    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping()
    public WeatherResponse search(@RequestParam Optional<String> name, @RequestParam Optional<Integer> page) {
        return this.weatherService.getWeatherRecords(name, page);
    }
}
