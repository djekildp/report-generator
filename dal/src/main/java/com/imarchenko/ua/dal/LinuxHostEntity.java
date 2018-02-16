package com.imarchenko.ua.dal;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(columnList = ("name"),name="LinuxHostEntity_name_index", unique=false)})
public class LinuxHostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String host;
    private int port;
    @OneToOne
    private SshUser sshUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public SshUser getSshUser() {
        return sshUser;
    }

    public void setSshUser(SshUser sshUser) {
        this.sshUser = sshUser;
    }
}
