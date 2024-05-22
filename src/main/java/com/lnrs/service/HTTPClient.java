package com.lnrs.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Getter
@Setter
@Component

public class HTTPClient {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String apiValue;

    @Autowired
    public HTTPClient(RestTemplate restTemplate, @Value("${api.key}") String apiKey, @Value("${api.value}") String apiValue) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.apiValue = apiValue;
    }

    public ResponseEntity<String> get(String url) {
        ResponseEntity<String> responseEntity;
        HttpHeaders headers = new HttpHeaders();
        headers.set(apiKey,apiValue);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        responseEntity =  restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return responseEntity;
    }

}
