package com.github.maheshyaddanapudi.redhat.ansibledocsboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String swaggerUI() {
        return "redirect:/swagger-ui.html";
    }
}
