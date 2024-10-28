package com.example.exe201.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {
    @GetMapping("/Check-out")
    public String checkOut(){
        return "checkout";
    }
}
