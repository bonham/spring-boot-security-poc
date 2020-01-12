package com.example.spring;

import org.springframework.web.bind.annotation.*;

@RestController
public class Example {

    @RequestMapping("/1")
    String home() {
        return "Helloox World!";
    }

}