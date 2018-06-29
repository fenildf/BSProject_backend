package bsproject.backend.models;

import bsproject.backend.entities.Recited;
import org.jetbrains.annotations.NotNull;

/**
 * author: yusong wu
 * */

// currently not used
public class RecitedModel {
    private Integer id;
    private String english;
    private String chinese;
    private String example;

    public RecitedModel(){ }

    public RecitedModel(@NotNull Recited recited){
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
