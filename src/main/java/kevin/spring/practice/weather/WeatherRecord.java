package kevin.spring.practice.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

@JsonPropertyOrder({ "page", "per_page", "total", "total_pages", "data" })
public class WeatherRecord {
    static Pattern weather_pattern = Pattern.compile("(-?\\d+)");
    static Pattern wind_pattern = Pattern.compile("Wind: (\\d+)");
    static Pattern humidity_pattern = Pattern.compile("Humidity: (\\d+)");

    @JsonProperty

    String name;
    @JsonProperty
    String weather;
    @JsonProperty
    String[] status;

    public WeatherRecord() {

    }

    public WeatherRecord(String name, String weather, String[] status) {
        this.name = name;
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    @JsonIgnore
    public int getWind() {
        return Integer.parseInt(wind_pattern.matcher(this.status[0]).group(0));
    }

    @JsonIgnore
    public int getHumidity() {
        return Integer.parseInt(humidity_pattern.matcher(status[1]).group(0));
    }

    public static List<WeatherRecord> loadFromFile() {
        return loadFromFile("./datasources/hackerrank_weather.json");
    }

    public static List<WeatherRecord> loadFromFile(String path) {
        try {
            String json = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
}