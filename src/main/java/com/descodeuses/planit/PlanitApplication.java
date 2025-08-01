package com.descodeuses.planit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
    "com.descodeuses.planit.entity", // ✅ pour Action
    
})
public class PlanitApplication {        
    public static void main(String[] args) {
        SpringApplication.run(PlanitApplication.class, args);
    }
}

