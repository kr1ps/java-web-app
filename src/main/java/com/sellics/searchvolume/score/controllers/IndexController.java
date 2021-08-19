package com.sellics.searchvolume.score.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        final String s = "index.html";
        return s;
    }
}
