package dev.zepnex.utils;

import com.google.gson.annotations.SerializedName;

public class BotInformation {
    private String token;
    private Database database;


    public BotInformation(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Database getDatabase() {
        return database;
    }

    public class Database {
        private String user;
        private String password;

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }
    }
}

