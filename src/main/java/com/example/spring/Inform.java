package com.example.spring;

import org.springframework.web.bind.annotation.*;

@RestController
public class Inform {

    @RequestMapping("/inform")
    String home() {
        return "Be informed";
    }


}