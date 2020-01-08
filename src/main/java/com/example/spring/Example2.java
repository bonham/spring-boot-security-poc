package com.example.spring;

import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Example2 {

    @RequestMapping("/2")
    SmallObj home() {
    	
    	SmallObj s = new SmallObj();
    	s.setLevel(8);
    	s.setLabel("Radius");
    	return s;
    }


}