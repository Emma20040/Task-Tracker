package com.emma.Task;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping(path = "/test")
    public String hello(){
        return "Testing !!";

    }
}