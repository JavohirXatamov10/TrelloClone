package org.example.imtihon_8_modul.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class PageController {
    @GetMapping
    public String test(){
        return "introductionPage";
    }
}
