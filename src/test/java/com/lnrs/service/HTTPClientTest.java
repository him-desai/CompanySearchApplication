package com.lnrs.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HTTPClientTest {

    @Mock
    private RestTemplate restTemplate;

    private HTTPClient httpClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        httpClient = new HTTPClient(restTemplate, "dummyKey", "dummyVal");
    }

    @Test
    public void testGet_Success() {
        String url = "http://www.google.com";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Test Response", HttpStatus.OK);
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(expectedResponse);
        ResponseEntity<String> responseEntity = httpClient.get(url);
        assertEquals(expectedResponse, responseEntity);
    }

}
