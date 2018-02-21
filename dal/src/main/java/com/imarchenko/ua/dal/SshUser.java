package com.imarchenko.ua.dal;

import javax.persistence.*;

@Entity
public class SshUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userName;
    private String password;
    private String prvkey;
    private String phrase;
    private Type type;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum Type{
        USER(1, "Authorization by user and password."),
        KEY(2, "Authorization by user and key.");

        private int type;
        private String value;
        Type(int type, String value) {
            this.type = type;
            this.value = value;
        }

        public int getType() {
            return type;
        }

        public String getValue() {
            return value;
        }

        public static Type valueOf(int typeCode){
            Type [] types = values();
            int length = types.length;

            for(int i = 0; i < length; i++){
                Type type = types[i];
                if(type.type == typeCode){
                    return type;
                }
            }
            throw new IllegalArgumentException("No matching constant for [" + typeCode + "]");
        }
    }

}
