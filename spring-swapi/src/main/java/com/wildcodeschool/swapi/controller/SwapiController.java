package com.wildcodeschool.swapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildcodeschool.swapi.model.People;
import com.wildcodeschool.swapi.model.Planet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.websocket.server.PathParam;

@RestController
public class SwapiController {

    private static final String SWAPI_URL = "https://swapi.dev/api";
    private WebClient webClient = WebClient.create(SWAPI_URL);

    @GetMapping("/planet")
    public Planet planet(Model model, @RequestParam String id) {
        ObjectMapper objectMapper = new ObjectMapper();
        Planet planetObject = new Planet();
        // TODO : call the API and retrieve the planet

        Mono<String> call = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/planets/{planets}/")
                        .build(id))
                .retrieve()
                .bodyToMono(String.class);

        String response = call.block();

        try {
            planetObject = objectMapper.readValue(response, Planet.class);
        }  catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return planetObject;
    }

    @GetMapping("/people")
    public String people(Model model, @RequestParam Long id) {

        WebClient webClient = WebClient.create(SWAPI_URL);
        Mono<String> call = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/people/{id}/")
                        .build(id))
                .retrieve()
                .bodyToMono(String.class);

        String response = call.block();

        ObjectMapper objectMapper = new ObjectMapper();
        People peopleObject = null;
        try {
            peopleObject = objectMapper.readValue(response, People.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        model.addAttribute("peopleInfos", peopleObject);

        return "people";
    }
}
