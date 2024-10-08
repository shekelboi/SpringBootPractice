//package kevin.spring.practice.weather;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Configuration
//public class WeatherConfig {
//    @Order(1)
//    @Bean
//    CommandLineRunner loadWeatherData(WeatherRepository repository) {
//        return args -> {
//            List<WeatherRecord> weatherRecords = WeatherRecord.loadFromFile();
//            repository.saveAll(weatherRecords);
//        };
//    }
//}
