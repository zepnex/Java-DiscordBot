package dev.zepnex.utils;

public class BotInformation {
    private String token;
    private String databaseUser;
    private String databasePassword;

    public BotInformation(String token, String databaseUser, String databasePassword) {
        this.token = token;
        this.databaseUser = databaseUser;
        this.databasePassword = databasePassword;

    }

    public String getToken() {
        return token;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }


}
