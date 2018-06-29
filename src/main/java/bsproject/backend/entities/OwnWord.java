package bsproject.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author wu yusong
 */

@Entity
public class OwnWord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String english;
    private String chinese;
    private String example;
    private String level;

    // the foreign key is "uid"
    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn(name = "uid")
    private User user;

    public OwnWord(){}

    public OwnWord(User user){
        this.user = user;
    }

    // some boring get and set...
    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getEnglish(){
        return english;
    }

    public void setEnglish(String english){
        this.english = english;
    }

    public String getChinese(){
        return chinese;
    }

    public void setChinese(String chinese){
        this.chinese = chinese;
    }

    public String getExample(){
        return example;
    }

    public void setExample(String example){
        this.example = example;
    }

    public String getLevel(){
        return level;
    }

    public void setLevel(String level){
        this.level = level;
    }

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }
}