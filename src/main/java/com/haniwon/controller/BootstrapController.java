package com.haniwon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BootstrapController {

    @GetMapping("/hello")
    public String dashboard() {
        return "index";
    }

}
