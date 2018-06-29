package bsproject.backend.controllers;

import bsproject.backend.exceptions.WordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import bsproject.backend.entities.User;
import bsproject.backend.repositories.UserRepository;
import bsproject.backend.entities.CET4;
import bsproject.backend.repositories.CET4Repository;
import bsproject.backend.entities.Recited;
import bsproject.backend.repositories.RecitedRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author yusong wu
 * */

@Controller
@RequestMapping(path="/cet4")
public class CET4Controller{
    private final CET4Repository cet4Repository;
    private final RecitedRepository recitedRepository;
    private final UserRepository userRepository;
    /*
    String english;
    String chinese;
    String example;
    Boolean isRecited;
    Float errorRate;
    Integer level;
    */
    @Autowired
    public CET4Controller(CET4Repository cet4Repository, RecitedRepository recitedRepository,
                              UserRepository userRepository) {
        this.cet4Repository = cet4Repository;
        this.recitedRepository = recitedRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path="/add")
    public @ResponseBody String addNewEnglishWord(
            @RequestParam String english,
            @RequestParam String chinese,
            @RequestParam String example,
            @RequestParam Integer errorCount){
        CET4 eng = new CET4();
        eng.setEnglish(english);
        eng.setChinese(chinese);
        eng.setExample(example);
        eng.setErrorCount(errorCount);
        cet4Repository.save(eng);
        return "new cet4 word created!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<CET4> getAllEnglishWords(){
        return cet4Repository.findAll();
    }


    @GetMapping(path = "/deleteAll")
    public @ResponseBody String deleteAllEnglishWords(){
        cet4Repository.deleteAll();
        return "deleted all the words!";
    }

    @GetMapping("/find/{id}")
    public @ResponseBody CET4 FindWordById(@PathVariable int id){
        return cet4Repository.findById(id).orElseThrow(WordNotFoundException::new);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeEnglishWord(@PathVariable int id){
        // delete word according to id
        // this word should be put in recitedRepository
        CET4 cet4Entity = cet4Repository.findById(id).orElseThrow(WordNotFoundException::new);
        cet4Repository.delete(cet4Entity);
    }

    @GetMapping(path = "/cet4Count")
    public @ResponseBody Integer getCurCET4Count(){
        return cet4Repository.findAll().size();
    }
}
