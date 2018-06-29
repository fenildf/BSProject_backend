package bsproject.backend.responses;

import org.jetbrains.annotations.Nls;

public class AddOwnWordResponse {
    private final @Nls
    String status;

    public AddOwnWordResponse(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}