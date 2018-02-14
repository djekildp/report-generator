package com.imarchenko.ua.dto;

public class SshUserRequestDto {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrvkey() {
        return prvkey;
    }

    public void setPrvkey(String prvkey) {
        this.prvkey = prvkey;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

}
