package bsproject.backend.requests;

public class AddOwnWordRequest {
    private String chinese;
    private String english;
    private String example;
    private String level;
    private Integer uid;

    public String getChinese(){
        return chinese;
    }

    public String getEnglish() {
        return english;
    }

    public String getExample() {
        return example;
    }

    public String getLevel(){
        return level;
    }

    public Integer getUid(){
        return uid;
    }
}
