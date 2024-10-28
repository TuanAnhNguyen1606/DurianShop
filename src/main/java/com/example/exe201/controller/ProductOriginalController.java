package com.example.exe201.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductOriginalController {
    @GetMapping("/ProductOriginal")
    public String original(){
        return "product-original";
    }

}
