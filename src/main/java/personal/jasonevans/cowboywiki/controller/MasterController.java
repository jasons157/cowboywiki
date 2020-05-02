package personal.jasonevans.cowboywiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MasterController {

    @GetMapping("/")
    public String showHome(){

        return "home";
    }

}
