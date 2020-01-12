package com.example.spring;

import org.springframework.web.bind.annotation.*;

@RestController
//@EnableAutoConfiguration
public class Inform {

    @RequestMapping("/inform")
    String home() {
        return "Be informed";
    }


}