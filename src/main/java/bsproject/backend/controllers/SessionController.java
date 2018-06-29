package bsproject.backend.controllers;


import bsproject.backend.annotations.Authorization;
import bsproject.backend.annotations.CurrentUser;

import bsproject.backend.entities.Session;
import bsproject.backend.entities.User;
import bsproject.backend.repositories.SQLSessionRepository;
import bsproject.backend.repositories.UserRepository;

import bsproject.backend.requests.LoginRequest;
import bsproject.backend.responses.LoginResponse;
import bsproject.backend.utils.SecurityUtils;
import bsproject.backend.utils.SessionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "/session")
public class SessionController {
    private final UserRepository userRepository;
    private final SQLSessionRepository sqlSessionRepository;

    @Autowired
    public SessionController(UserRepository userRepository, SQLSessionRepository sqlSessionRepository) {
        this.userRepository = userRepository;
        this.sqlSessionRepository = sqlSessionRepository;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest login) {
        if (!userRepository.existsByEmail(login.getEmail())) {
            return new ResponseEntity<>(new LoginResponse("", "", "", "User not exists"), HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByEmail(login.getEmail());
        if (!user.getHashedPassword().equals(SecurityUtils.getHashedPasswordByPasswordAndSalt(login.getPassword(), user.getSalt()))) {
            return new ResponseEntity<>(new LoginResponse("", "", user.getName(), "Password incorrect"), HttpStatus.BAD_REQUEST);
        }

        Session session = new Session();
        session.setEmail(login.getEmail());
        session.setToken(SessionUtils.getToken());
        session.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        if (sqlSessionRepository.existsByEmail(login.getEmail())) {
            Session sessionToRemove = sqlSessionRepository.findByEmail(login.getEmail());
            sqlSessionRepository.delete(sessionToRemove);
        }
        sqlSessionRepository.save(session);
        return new ResponseEntity<>(new LoginResponse(login.getEmail(), session.getToken(), user.getName(), "OK"), HttpStatus.OK);
    }

    /**
     * TODO
     */
    @PostMapping(path = "logout")
    @Authorization
    public ResponseEntity logout(@CurrentUser User user) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}