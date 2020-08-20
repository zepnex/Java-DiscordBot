package dev.zepnex.utils;

public class BotInformation {
    private String token;
    private String user;
    private String password;

    public BotInformation(String token, String user, String password) {
        this.token = token;
        this.user = user;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
