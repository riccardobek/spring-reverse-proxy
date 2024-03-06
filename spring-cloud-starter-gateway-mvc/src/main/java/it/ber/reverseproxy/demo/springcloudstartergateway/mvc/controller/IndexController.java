package it.ber.reverseproxy.demo.springcloudstartergateway.mvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private static final String home = "index";

    @GetMapping("/")
    public String indexPage(Model model) {
        return home;
    }

}
