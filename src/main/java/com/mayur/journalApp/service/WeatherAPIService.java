package com.mayur.journalApp.service;

import com.mayur.journalApp.api.response.WeatherResponse;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherAPIService {

    @Value("${weather.api.key}")
    private String apiKey;

    private static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI = API.replace("CITY",city).replace("API_KEY",apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
//    //For post API call on other projects from this project
//    public WeatherResponse postWeather(String city){
//        String finalAPI = API.replace("CITY",city).replace("API_KEY",apiKey);
// //        We can send headers as well in this
//        String requestBody = "{\n" +
//                "    \"userName\":\"Tambe\",\n" +
//                "    \"password\": \"Tambe\"\n" +
//                "}";
//        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody);
//
// //        UserDetails user = User.builder().username("Mayur").password("Mayur").build();
// //        HttpEntity<UserDetails> httpEntity1 = new HttpEntity<>(user);
//
//        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherResponse.class);
//        WeatherResponse body = response.getBody();
//        return body;
//    }

}

//RestTemplate is a synchronous HTTP client provided by the Spring Framework. It simplifies interaction with external RESTful web services by abstracting low-level HTTP details like opening connections, setting headers, and handling JSON/XML conversions
