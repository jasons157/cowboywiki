package personal.jasonevans.cowboywiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import personal.jasonevans.cowboywiki.entity.Cowboy;
import personal.jasonevans.cowboywiki.service.CowboyService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/cowboys")
public class CowboyController {

    private CowboyService cowboyService;

    @Autowired
    public CowboyController(CowboyService theCowboyService){
        cowboyService = theCowboyService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

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

    @PostMapping("/processSaveCowboy")
    public String processSaveCowboy(
            @Valid @ModelAttribute("newCowboy") Cowboy cowboy,
            BindingResult bindingResult,
            Model model){

        String firstName = cowboy.getFirstName();
        String lastName = cowboy.getLastName();

        if (bindingResult.hasErrors()){
            return "cowboys/save-cowboy";
        }

        if (firstAndLastExist(firstName, lastName)){
            model.addAttribute("cowboy", new Cowboy());//Taken, set up new cowboy in form
            model.addAttribute("cowboyError", "Cowboy with that first and last name already exist.");

            return "cowboys/save-cowboy";
        }

        //everything passed then we're good
        cowboyService.save(cowboy);

        return "cowboys/cowboy-confirmation";
    }

    //TODO when updating and saving, birth and death dates move back a day

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("cowboyId") int theId, Model theModel){

        Cowboy theCowboy = cowboyService.findCowboyById(theId);

        theModel.addAttribute("newCowboy", theCowboy);

        return "cowboys/save-cowboy";
    }

    //TODO allow name to be same when updating
    //Utility method for checking
    public boolean firstAndLastExist(String firstName, String lastName){

        List<Cowboy> cowboyList = new ArrayList<>();

        cowboyList = cowboyService.findAll();

        //Loop through all cowboys and see if any match names
        for (Cowboy tempCowboy : cowboyList){
            if (tempCowboy.getFirstName().toUpperCase().equals(firstName.toUpperCase()) &&
                tempCowboy.getLastName().toUpperCase().equals(lastName.toUpperCase())){
                return true;
            }
        }

        return false;
    }

}
