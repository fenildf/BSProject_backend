package bsproject.backend.models;

import bsproject.backend.entities.OwnWord;
import org.jetbrains.annotations.NotNull;

// currently not used
public class OwnWordModel {
    private Integer id;
    private String english;
    private String chinese;
    private String example;

    public OwnWordModel(){ }

    public OwnWordModel(@NotNull OwnWord recited){
        id = recited.getId();
        english = recited.getEnglish();
        chinese = recited.getChinese();
        example = recited.getExample();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
