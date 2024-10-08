package kevin.spring.practice.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class WeatherService {
    private final List<WeatherRecord> weatherRecords;

    public WeatherService() {
        this.weatherRecords = WeatherRecord.loadFromFile();
    }

    public WeatherResponse getWeatherRecords(Optional<String> name, Optional<Integer> page) {
        WeatherResponse.Builder builder = new WeatherResponse.Builder().data(this.weatherRecords);
        if (name.isPresent()) {
            builder.name(name.get());
        }
        if (page.isPresent()) {
            builder.page(page.get());
        }
        return builder.build();
    }
}
