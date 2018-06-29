package bsproject.backend.controllers;

import bsproject.backend.entities.CET4;
import bsproject.backend.exceptions.WordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import bsproject.backend.entities.CET6;
import bsproject.backend.repositories.CET6Repository;

/**
 * @author yusong wu
 * */

@Controller
@RequestMapping(path="/cet6")
public class CET6Controller{
    private final CET6Repository cet6Repository;
    /*
    String english;
    String chinese;
    String example;
    Boolean isRecited;
    Float errorRate;
    Integer level;
    */
    @Autowired
    public CET6Controller(CET6Repository cet6Repository){
        this.cet6Repository = cet6Repository;
    }

    @GetMapping(path="/add")
    public @ResponseBody String addNewEnglishWord(
            @RequestParam String english,
            @RequestParam String chinese,
            @RequestParam String example,
            @RequestParam Integer errorCount){
        CET6 eng = new CET6();
        eng.setEnglish(english);
        eng.setChinese(chinese);
        eng.setExample(example);
        eng.setErrorRate(errorCount);
        cet6Repository.save(eng);
        return "new cet6 word created!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<CET6> getAllEnglishWords(){
        return cet6Repository.findAll();
    }

    @GetMapping(path = "/deleteAll")
    public @ResponseBody String deleteAllEnglishWords(){
        // this deletes all the words in the repo
        cet6Repository.deleteAll();
        return "deleted all the words!";
    }

    @GetMapping("/find/{id}")
    public @ResponseBody CET6 FindWordById(@PathVariable int id){
        return cet6Repository.findById(id).orElseThrow(WordNotFoundException::new);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeEnglishWord(@PathVariable int id){
        // delete word according to id
        // and then put this word to the table called 'recited'
        CET6 cet6Entity = cet6Repository.findById(id).orElseThrow(WordNotFoundException::new);
        cet6Repository.delete(cet6Entity); // delete this word in its original table
    }

    @GetMapping("/cet6Count")
    public @ResponseBody Integer getCurCET6Count(){
        return cet6Repository.findAll().size();
    }

}
