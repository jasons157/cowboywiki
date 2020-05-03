package personal.jasonevans.cowboywiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.jasonevans.cowboywiki.service.CowboyService;

@Controller
@RequestMapping("/cowboys")
public class CowboyController {

    private CowboyService cowboyService;

    @Autowired
    public CowboyController(CowboyService theCowboyService){
        cowboyService = theCowboyService;
    }

    @GetMapping("/list")
    public String showCowboys(Model model){

        model.addAttribute("cowboys", cowboyService.findAll());

        return "cowboys/cowboy-list";
    }


}
