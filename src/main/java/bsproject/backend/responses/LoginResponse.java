package bsproject.backend.responses;

import org.jetbrains.annotations.Nls;

public class LoginResponse {
    //private final Integer id;
    private final String email;
    private final String token;
    private final String username;
    private final @Nls
    String info;

    public LoginResponse(String email, String token, String username, String info) {
        this.email = email;
        this.token = token;
        this.username = username;
        this.info = info;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getInfo() {
        return info;
    }

    public String getUsername() {
        return username;
    }
}