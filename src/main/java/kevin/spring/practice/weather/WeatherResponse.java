package kevin.spring.practice.weather;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class WeatherResponse {
    @JsonProperty
    int page;
    @JsonProperty("per_page")
    int perPage;
    @JsonIgnore
    List<WeatherRecord> data;

    public static class Builder {

        private int page = 1;
        private int perPage = 10;
        private List<WeatherRecord> data = Collections.emptyList();
        private Optional<String> name = Optional.empty();

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder perPage(int perPage) {
            this.perPage = perPage;
            return this;
        }

        public Builder data(List<WeatherRecord> data) {
            this.data = data;
            return this;
        }

        public Builder name(String name) {
            this.name = Optional.of(name);
            return this;
        }

        WeatherResponse build() {
            return new WeatherResponse(this);
        }
    }

    private WeatherResponse(Builder builder) {
        this.page = builder.page;
        this.perPage = builder.perPage;
        this.data = builder.name.isPresent() ? filterData(builder.data, builder.name.get()) : builder.data;
    }
    public WeatherResponse() {
    }

    public WeatherResponse(List<WeatherRecord> data, String name, int page) {
        this(page, 10, filterData(data, name));
    }

    public WeatherResponse(List<WeatherRecord> data, String name) {
        this(filterData(data, name));
    }

    public WeatherResponse(List<WeatherRecord> weatherRecords) {
        this(1, 10, weatherRecords);
    }
    public WeatherResponse(int page, int perPage, List<WeatherRecord> data) {
        this.page = page;
        this.perPage = perPage;
        this.data = data;
    }


    private static List<WeatherRecord> filterData(List<WeatherRecord> data, String name) {
        return data.stream().filter(w -> Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE).matcher(w.name).find()).toList();
    }

    @JsonProperty("total")
    public int getTotal() {
        return this.data.size();
    }

    @JsonProperty("total_pages")
    public int getTotalPages() {
        return (int) Math.ceil((float)this.getTotal() / this.perPage);
    }

    @JsonProperty("data")
    public List<WeatherRecord> getSelectedRecords() {
        int fromIndex = (this.page - 1) * this.perPage;
        int toIndex = Math.min(this.page * this.perPage, getTotal());
        if (this.page < 0 || this.page > getTotalPages()) {
            return Collections.emptyList();
        }
        return data.subList(fromIndex, toIndex);
    }
}
