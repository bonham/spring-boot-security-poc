package com.example.spring;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Example {

    @RequestMapping("/1")
    String home() {
        return "Helloox World!";
    }

}