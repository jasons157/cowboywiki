package personal.jasonevans.cowboywiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.jasonevans.cowboywiki.entity.Cowboy;
import personal.jasonevans.cowboywiki.service.CowboyService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CowboyRestController {

    @Autowired
    CowboyService cowboyService;

    @GetMapping("/cowboys")
    public List<Cowboy> getCowboys(){

        return cowboyService.findAll();
    }

    @GetMapping("/cowboys/{id}")
    public Cowboy getCowboy(@PathVariable int id){
        return cowboyService.findCowboyById(id);
    }

}
