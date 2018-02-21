package com.imarchenko.ua.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class SshUserDo {
    private final static Properties properties;
    static {
        properties = new Properties();
        InputStream inputStream = SshUserDo.class.getClassLoader().getResourceAsStream("domain.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final static int DEFAULT_NAME_MAX_LENGTH = 30;
    private final static int NAME_MAX_LENGTH = Integer.parseInt(properties.getProperty("user.name.length.max", String.valueOf(DEFAULT_NAME_MAX_LENGTH)));
    private final static int DEFAULT_NAME_MIN_LENGTH = 3;
    private final static int NAME_MIN_LENGTH = Integer.parseInt(properties.getProperty("user.name.length.min", String.valueOf(DEFAULT_NAME_MIN_LENGTH)));

    private Long id;
    private String userName;
    private String password;
    private String prvkey;
    private String phrase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName == null){
            throw new NullPointerException("Parameter \"userName\" can't be empty!");
        }
        if(!isCorectNameLength(userName)){
            throw new IllegalArgumentException("Parameter \"userName\" is incorrect length min=" + NAME_MIN_LENGTH + " max=" + NAME_MAX_LENGTH);
        }
        this.userName = userName;
    }

    private boolean isCorectNameLength(String userName) {
        int nameLength = userName.length();
        return NAME_MIN_LENGTH <= nameLength && nameLength <= NAME_MAX_LENGTH;
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
