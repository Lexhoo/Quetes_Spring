package com.example.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String hello() {
        return ("Welcome to the SHIELD !!!");
    }

    @GetMapping("/avengers/assemble")
    public String avenger() {
        return ("Avengers... Assemble!!!!");
    }

    @GetMapping("/secret-bases")
    public List<String> secret() {
        List<String> listWcs= Arrays.asList("Biarritz", "Bordeaux", "La Loupe", "Lyon", "Marseille", "Nantes", "Lisbonne", "Barcelone");
        return listWcs;
    }
}
