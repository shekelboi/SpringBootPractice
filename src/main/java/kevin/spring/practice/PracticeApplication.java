package kevin.spring.practice;

import kevin.spring.practice.weather.WeatherController;
import kevin.spring.practice.weather.WeatherRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}
}
