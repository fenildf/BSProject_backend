package bsproject.backend.responses;

import org.jetbrains.annotations.Nls;

public class GetRecitedResponse {
    private final Integer id;
    private final String english;
    private final String chinese;
    private final String example;
    private final @Nls
    String info;

    public GetRecitedResponse(Integer id,String english, String chinese, String example, String info) {
        this.id = id;
        this.english = english;
        this.chinese = chinese;
        this.example = example;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public String getEnglish() {
        return english;
    }

    public String getChinese() {
        return chinese;
    }

    public String getExample() {
        return example;
    }
}
