package com.example.sheltermanagment.xport;


import com.example.sheltermanagment.domain.value_objects.Pet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import java.util.Collections;
import java.util.List;

@Service
public class PetClient {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public PetClient(@Value("${app.pet-catalog.url}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri() {
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Pet> findAll() {
        try {
            return restTemplate.exchange(uri().path("/api/pet").build().toUri(), HttpMethod.GET,null, new ParameterizedTypeReference<List<Pet>>() {
            }).getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}

