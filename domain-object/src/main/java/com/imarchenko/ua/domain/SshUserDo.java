package com.imarchenko.ua.domain;

public class SshUserDo {
    private long id;
    private String userName;
    private String password;
    private String prvkey;
    private String phrase;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
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
