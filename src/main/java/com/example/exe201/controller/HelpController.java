package com.example.exe201.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Help")
public class HelpController {
    @GetMapping("/Return")
    public String returnPage(){
        return "help/ReturnAndExchange";
    }
    @GetMapping("/Term")
    public String termPage(){
        return "help/term";
    }
    @GetMapping("/Privacy")
    public String privacyPage(){
        return "help/privacy";
    }
}
