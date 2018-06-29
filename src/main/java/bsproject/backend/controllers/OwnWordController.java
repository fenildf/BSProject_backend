package bsproject.backend.controllers;

import bsproject.backend.entities.User;
import bsproject.backend.exceptions.UserNotFoundException;
import bsproject.backend.exceptions.WordNotFoundException;
import bsproject.backend.repositories.UserRepository;
import bsproject.backend.requests.AddOwnWordRequest;
import bsproject.backend.responses.AddOwnWordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import bsproject.backend.entities.OwnWord;
import bsproject.backend.repositories.OwnWordRepository;

/**
 * @author wu yusong
 * this entity defines the words defined by the user him/herself
 */

@Controller
@RequestMapping(path="/ownword")
public class OwnWordController{
    /*
    Integer id;
    String english;
    String chinese;
    String example;
    Integer uid; // implicitly defined in User.java
    String level;
    * */
    private final OwnWordRepository ownWordRepository;

    @Autowired
    public OwnWordController(OwnWordRepository ownWordRepository){
        this.ownWordRepository = ownWordRepository;
    }

    @GetMapping("/find/{id}")
    public @ResponseBody
    OwnWord FindWordById(@PathVariable int id){
        return ownWordRepository.findById(id).orElseThrow(WordNotFoundException::new);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<OwnWord> getAllOwnWords(){
        return ownWordRepository.findAll();
    }

    @GetMapping(path = "/deleteAll")
    public @ResponseBody String deleteAllOwnWords(){
        ownWordRepository.deleteAll();
        return "deleted all your own words!";
    }
}