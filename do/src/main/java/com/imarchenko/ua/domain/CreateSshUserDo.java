package com.imarchenko.ua.domain;

public class CreateSshUserDo {
    private String userName;
    private String password;
    private String prvkey;
    private String phrase;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName.isEmpty()){
            throw new NullPointerException("User name can't be empty.");
        }
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrvkey(String prvkey) {
        this.prvkey = prvkey;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getPassword() {
        return password;
    }

    public String getPrvkey() {
        return prvkey;
    }

    public String getPhrase() {
        return phrase;
    }
}
