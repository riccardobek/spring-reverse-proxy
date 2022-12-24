package it.ber.reverseproxy.demo.springcloudstarternetflixzuul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private static final String indexPage = "index";

    @GetMapping("/")
    public String getIndexPage(){
        return indexPage;
    }

}
