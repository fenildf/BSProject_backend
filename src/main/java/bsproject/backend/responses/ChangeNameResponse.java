package bsproject.backend.responses;

import org.jetbrains.annotations.Nls;

public class ChangeNameResponse {
    private final String username;
    private final @Nls
    String status;

    public ChangeNameResponse(String status, String username){
        this.status = status;
        this.username = username;
    }

    public String getStatus(){
        return status;
    }

    public String getUsername(){
        return username;
    }
}
