package com.test.blogricetter.controller;


import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RicettaController {
    @GetMapping
    public String home(Model model){
        return "homepage";
    }
}
