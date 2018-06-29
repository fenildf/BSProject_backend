package bsproject.backend.controllers;

import bsproject.backend.configs.Config;
import bsproject.backend.entities.CET4;
import bsproject.backend.entities.OwnWord;
import bsproject.backend.entities.Recited;
import bsproject.backend.exceptions.UserNotFoundException;
import bsproject.backend.exceptions.WordNotFoundException;
import bsproject.backend.repositories.OwnWordRepository;
import bsproject.backend.repositories.RecitedRepository;
import bsproject.backend.requests.AddOwnWordRequest;
import bsproject.backend.requests.AddRecitedRequest;
import bsproject.backend.requests.AddUserRequest;
import bsproject.backend.requests.ChangeNameRequest;
import bsproject.backend.responses.AddOwnWordResponse;
import bsproject.backend.responses.AddRecitedResponse;
import bsproject.backend.responses.AddUserResponse;
import bsproject.backend.responses.ChangeNameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import bsproject.backend.entities.User;
import bsproject.backend.repositories.UserRepository;

import java.util.List;

import static bsproject.backend.utils.SecurityUtils.getHashedPasswordByPasswordAndSalt;
import static bsproject.backend.utils.SecurityUtils.getSalt;

/**
 * @author yusong wu
 * */

@Controller
@RequestMapping(path="/user")
public class UserController{
    private final UserRepository userRepository;
    private final RecitedRepository recitedRepository;
    private final OwnWordRepository ownWordRepository;

    @Autowired
    public UserController(UserRepository userRepository,
                          RecitedRepository recitedRepository,
                          OwnWordRepository ownWordRepository){
        this.userRepository = userRepository;
        this.recitedRepository = recitedRepository;
        this.ownWordRepository = ownWordRepository;
    }

    // create user with hashed password
    @PutMapping("/create")
    public ResponseEntity<AddUserResponse> createUser(@RequestBody AddUserRequest user) {
        String email = user.getEmail();
        if (userRepository.existsByEmail(email)) {
            return new ResponseEntity<>(new AddUserResponse("failed with duplicated email", "", ""), HttpStatus.BAD_REQUEST);
        }
        String name = user.getUsername();
        if (userRepository.existsByName(name)) {
            return new ResponseEntity<>(new AddUserResponse("failed with duplicated user name", "", ""), HttpStatus.BAD_REQUEST);
        }
        User entity = new User();
        entity.setEmail(email);
        entity.setName(user.getUsername());
        String salt = getSalt();
        String hashedPassword = getHashedPasswordByPasswordAndSalt(user.getPassword(), salt);
        entity.setSalt(salt);
        entity.setHashedPassword(hashedPassword);
        userRepository.save(entity);
        return new ResponseEntity<>(new AddUserResponse("OK", email, user.getUsername()), HttpStatus.CREATED);
    }

    // get user id through email
    @GetMapping(path = "/find/{email}")
    public @ResponseBody Integer findUserId(@PathVariable String email) {
        if(userRepository.existsByEmail(email)){
            User user = userRepository.findByEmail(email);
            return user.getId();
        }
        return -1;
    }

    // get name through email
    @GetMapping(path = "/findName/{email}")
    public @ResponseBody String findUserName(@PathVariable String email) {
        if(userRepository.existsByEmail(email)){
            User user = userRepository.findByEmail(email);
            return user.getName();
        }
        return "Unknown User Name";
    }

    @PostMapping(path = "/changeName")
    public ResponseEntity<ChangeNameResponse> changeName(@RequestBody ChangeNameRequest change) {
        User user = userRepository.findById(change.getId()).orElseThrow(UserNotFoundException::new);
        user.setName(change.getUsername());
        return new ResponseEntity<>(new ChangeNameResponse("OK",change.getUsername()), HttpStatus.CREATED);
    }

    // add recited word for user
    @PostMapping(path = "/addRecited")
    public ResponseEntity<AddRecitedResponse> addRecited(@RequestBody AddRecitedRequest recited) {
        User user = userRepository.findById(recited.getUid()).orElseThrow(UserNotFoundException::new);
        Recited entity = new Recited(null);
        entity.setChinese(recited.getChinese());
        entity.setEnglish(recited.getEnglish());
        entity.setExample(recited.getExample());
        entity.setErrorCount(recited.getErrorCount());
        entity.setLevel(recited.getLevel());
        user.addRecitedWord(entity);
        recitedRepository.save(entity);//big fat BUG!!!
        return new ResponseEntity<>(new AddRecitedResponse("OK"), HttpStatus.CREATED);
    }

    // add own word for user
    @PostMapping(path = "/addOwnWord")
    public ResponseEntity<AddOwnWordResponse> addOwnWord(@RequestBody AddOwnWordRequest own) {
        User user = userRepository.findById(own.getUid()).orElseThrow(UserNotFoundException::new);
        OwnWord entity = new OwnWord(null);
        entity.setChinese(own.getChinese());
        entity.setEnglish(own.getEnglish());
        entity.setExample(own.getExample());
        entity.setLevel(own.getLevel());
        user.addOwnWord(entity);
        ownWordRepository.save(entity);//big fat BUG!!!
        return new ResponseEntity<>(new AddOwnWordResponse("OK"), HttpStatus.CREATED);
    }

    // delete own word
    @DeleteMapping(path = "deleteOwnWord/{id}")
    public @ResponseBody
    void DeleteById(@PathVariable Integer id) {
        if(ownWordRepository.existsById(id)){
            ownWordRepository.deleteById(id);
        }
    }

    // get a user's recited word through his/her id
    @GetMapping(path = "/{id}/recited")
    public @ResponseBody
    List<Recited> GetRecitedById(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return user.getRecitedWords();
    }

    // get a user's recited cet4 number
    @GetMapping(path = "/{id}/recitedCET4Count")
    public @ResponseBody
    Integer GetRecitedCET4CountById(@PathVariable Integer id) {
        Integer count=0;
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        List<Recited> all =  user.getRecitedWords();
        for(int i=0;i<all.size();i++){
            if(all.get(i).getLevel().equals("cet4")){
                count++;
            }
        }
        return count;
    }

    // get a user's recited cet6 number
    @GetMapping(path = "/{id}/recitedCET6Count")
    public @ResponseBody
    Integer GetRecitedCET6CountById(@PathVariable Integer id) {
        Integer count=0;
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        List<Recited> all =  user.getRecitedWords();
        for(int i=0;i<all.size();i++){
            if(all.get(i).getLevel().equals("cet6")){
                count++;
            }
        }
        return count;
    }

    // get all
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    // delete all
    @GetMapping(path = "/deleteAll")
    public @ResponseBody String deleteAllUsers(){
        userRepository.deleteAll();
        return "deleted all the users!";
    }
}
