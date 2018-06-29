package bsproject.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IELTZ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String english;
    private String chinese;
    private String example;
    private Boolean isRecited;
    private Float errorRate;
    private Integer level;

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

    public Boolean getIsRecited(){
        return isRecited;
    }

    public void setIsRecited(Boolean isRecited){
        this.isRecited = isRecited;
    }

    public Float getErrorRate(){
        return errorRate;
    }

    public void setErrorRate(Float errorRate){
        this.errorRate = errorRate;
    }

    public Integer getLevel(){
        return level;
    }

    public void setLevel(Integer level){
        this.level = level;
    }

}