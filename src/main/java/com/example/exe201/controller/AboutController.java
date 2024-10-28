package com.example.exe201.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/AboutUS")
    public String store(){
        return "about";
    }
}
