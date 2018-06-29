package bsproject.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author wu yusong
 * this entity defines the words defined by the user him/herself
 */

@Entity
public class Recited {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String english;
    private String chinese;
    private String example;
    private Integer errorCount;
    private String level;

    // the foreign key is "uid"
    // add JsonIgnore annotation to solve infinite recursion problem
    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn(name = "uid")
    private User user;

    public Recited(){}

    public Recited(User user){
        this.user = user;
    }

    // again...
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

    public Integer getErrorCount(){
        return errorCount;
    }

    public void setErrorCount(Integer errorCount){
        this.errorCount = errorCount;
    }

    public String getLevel(){
        return level;
    }

    public void setLevel(String level){
        this.level = level;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}