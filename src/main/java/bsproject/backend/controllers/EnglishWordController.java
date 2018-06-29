package bsproject.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bsproject.backend.entities.EnglishWord;
import bsproject.backend.repositories.EnglishWordRepository;

/*
 * @author yusong wu
 * */

@Controller
@RequestMapping(path="/englishword")
public class EnglishWordController{
    @Autowired
    private EnglishWordRepository englishWordRepository;
    /*
    String english;
    String chinese;
    String example;
    Boolean isRecited;
    Float errorRate;
    Integer level;
    */
    @GetMapping(path="/add")
    public @ResponseBody String addNewEnglishWord(
            @RequestParam String english,
            @RequestParam String chinese,
            @RequestParam String example,
            @RequestParam Boolean isRecited,
            @RequestParam Float errorRate,
            @RequestParam Integer level){
        EnglishWord eng = new EnglishWord();
        eng.setEnglish(english);
        eng.setChinese(chinese);
        eng.setExample(example);
        eng.setIsRecited(isRecited);
        eng.setErrorRate(errorRate);
        eng.setLevel(level);
        englishWordRepository.save(eng);
        return "new word created!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<EnglishWord> getAllEnglishWords(){
        // this returns a JSON or XML with the english words
        return englishWordRepository.findAll();
    }

    @GetMapping(path = "/deleteAll")
    public @ResponseBody String deleteAllEnglishWords(){
        // this deletes all the words in the repo
        englishWordRepository.deleteAll();
        return "deleted all the words!";
    }

}
