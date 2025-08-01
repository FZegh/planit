package com.descodeuses.planit.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utilisateur")

public class TestController {

    @GetMapping("/hello")
    public String HelloAction() {
        
        return "Hello utilisateur !";

    }


}
