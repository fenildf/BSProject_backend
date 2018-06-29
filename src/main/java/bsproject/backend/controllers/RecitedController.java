package bsproject.backend.controllers;

import bsproject.backend.entities.User;
import bsproject.backend.exceptions.UserNotFoundException;
import bsproject.backend.models.RecitedModel;
import bsproject.backend.requests.AddRecitedRequest;
import bsproject.backend.responses.AddRecitedResponse;
import bsproject.backend.responses.GetRecitedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import bsproject.backend.entities.User;
import bsproject.backend.repositories.UserRepository;
import bsproject.backend.entities.Recited;
import bsproject.backend.repositories.RecitedRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yusong wu
 * */

@Controller
@RequestMapping(path="/recited")
public class RecitedController{
    private final RecitedRepository recitedRepository;
    /*
    Integer id;
    String english;
    String chinese;
    String example;
    Integer errorCount;
    Integer uid; // implicitly defined in User.java
    String level;
    */
    @Autowired
    public RecitedController(RecitedRepository recitedRepository){
        this.recitedRepository = recitedRepository;
    }


    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Recited> getAllRecitedWords(){
        return recitedRepository.findAll();
    }

    @GetMapping(path = "/deleteAll")
    public @ResponseBody String deleteAllRecitedWords(){
        recitedRepository.deleteAll();
        return "deleted all the recited words!";
    }

}
