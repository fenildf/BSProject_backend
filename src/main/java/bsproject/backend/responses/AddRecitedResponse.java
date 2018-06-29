package bsproject.backend.responses;

import org.jetbrains.annotations.Nls;

public class AddRecitedResponse {
    private final @Nls
    String status;

    public AddRecitedResponse(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
