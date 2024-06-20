package com.example.wigellsushi.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyConverterService {
    @Autowired
    private RestTemplate restTemplate;

    public double convertSEKToEUR(double amount) {
        // Länk till converter api
        //https://www.exchangerate-api.com/docs/overview


        //Gå via local gateway
        String url = "http://localhost:7070/v6/1c5fb295f62bb439ba29f893/pair/SEK/EUR/" + amount;

        //Gå direkt via apiets hemsida
        //String url = "https://v6.exchangerate-api.com/v6/1c5fb295f62bb439ba29f893/pair/SEK/EUR/" + amount;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                //det här omvandlar json kroppen till noder som java kan arbeta med
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(response.getBody());
                double conversionResult = rootNode.path("conversion_result").asDouble();
                return conversionResult;

            } catch (Exception e) {
                System.out.println(e);
                return 0;
            }

        } else throw new RuntimeException("Failed to load response for exchangerate");
    }
}
