package personal.jasonevans.cowboywiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import personal.jasonevans.cowboywiki.entity.User;
import personal.jasonevans.cowboywiki.entity.WikiUser;
import personal.jasonevans.cowboywiki.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){

        StringTrimmerEditor editor = new StringTrimmerEditor(true);//Empty fields are considered null
        dataBinder.registerCustomEditor(String.class, editor);//use above editor as the databinder editor
    }

    /**
     * Form for registering new users to the database
     * @param model allows us to pass info to a related field in the actual web page
     * @return registration-form html
     */
    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model model){

        model.addAttribute("wikiUser", new WikiUser());//Pass new empty user to form

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("wikiUser") WikiUser wikiUser, //the user we're going to get back from the form
            BindingResult bindingResult, //for form validation
            Model model){

        String userName = wikiUser.getUsername();

        //perform form validation
        if (bindingResult.hasErrors()) return "registration-form";//issue with any validation on wikiUser, try form again

        //if username already exists then put out error and try form again
        if (userService.findByUserName(userName) != null){
            model.addAttribute("wikiUser", new WikiUser());//Pass new user back to form
            model.addAttribute("registrationError", "This username is already taken.");

            return "registration-form";
        }

        //if registration makes it to here, user is all good
        //has passed validation and has unique username
        userService.save(wikiUser);

        return "registration-confirmation";
    }
}
