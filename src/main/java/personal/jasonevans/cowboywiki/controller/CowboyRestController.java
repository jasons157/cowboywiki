package personal.jasonevans.cowboywiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/test")
    public String test(){return "Hello!!!";}

    @PostMapping("/cowboys")
    public ResponseEntity<String> saveCowboy(@RequestBody Cowboy cowboy){

        cowboy.setId(0);
        cowboyService.save(cowboy);

        return new ResponseEntity<String>("Cowboy created.", HttpStatus.CREATED);
    }

}
