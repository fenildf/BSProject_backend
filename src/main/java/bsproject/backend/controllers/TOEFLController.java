package bsproject.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bsproject.backend.entities.TOEFL;
import bsproject.backend.repositories.TOEFLRepository;

/**
 * @author yusong wu
 * */

@Controller
@RequestMapping(path="/toefl")
public class TOEFLController{
    @Autowired
    private TOEFLRepository toeflRepository;
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
        TOEFL eng = new TOEFL();
        eng.setEnglish(english);
        eng.setChinese(chinese);
        eng.setExample(example);
        eng.setIsRecited(isRecited);
        eng.setErrorRate(errorRate);
        eng.setLevel(level);
        toeflRepository.save(eng);
        return "new word created!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<TOEFL> getAllEnglishWords(){
        return toeflRepository.findAll();
    }

    @GetMapping(path = "/deleteAll")
    public @ResponseBody String deleteAllEnglishWords(){
        toeflRepository.deleteAll();
        return "deleted all the words!";
    }

}
