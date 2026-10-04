package com.mayur.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// this is deserialization - convert json to pojo/java object.

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;

        @JsonProperty("weather_description") //this is the josn property and I have to link with my own variable.. for camel case
        private List<String> weatherDescription;

        private int feelslike;

    }
}
