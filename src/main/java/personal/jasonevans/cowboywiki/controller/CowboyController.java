package personal.jasonevans.cowboywiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import personal.jasonevans.cowboywiki.entity.Cowboy;
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

    @GetMapping("showFullCowboy")
    public String showFullCowboy(@RequestParam("cowboyId") int theId, Model model){

        Cowboy theCowboy = cowboyService.findCowboyById(theId);

        model.addAttribute(theCowboy);

        return "cowboys/cowboy";
    }

    @GetMapping("saveCowboy")
    public String saveCowboy(Model model){

        model.addAttribute("newCowboy", new Cowboy());

        return "cowboys/save-cowboy";
    }


}
