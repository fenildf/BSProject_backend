package bsproject.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CET4 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String english;
    private String chinese;
    private String example;
    private Integer errorCount;

    public CET4(){
    }

    public CET4(String en, String ch, String ex, Integer errCnt) {
        this.english = en;
        this.chinese = ch;
        this.example = ex;
        this.errorCount = errCnt;
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

    public Integer getErrorCount(){
        return errorCount;
    }

    public void setErrorCount(Integer errorCount){
        this.errorCount = errorCount;
    }

}