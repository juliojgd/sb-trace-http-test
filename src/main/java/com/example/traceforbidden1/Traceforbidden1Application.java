package com.example.traceforbidden1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class Traceforbidden1Application {

    public static void main(String[] args) {
        SpringApplication.run(Traceforbidden1Application.class, args);
    }

    @RestController
    static class MyController {

        @GetMapping("/trace-test")
        public ResponseEntity<String> traceTest() {
            return ResponseEntity.ok("Right!");
        }
    }
}
