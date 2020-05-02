package personal.jasonevans.cowboywiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showLoginPage")
    public String showLoginPage(){

        return "login";
    }

    //TODO access denied mapping and page

}
