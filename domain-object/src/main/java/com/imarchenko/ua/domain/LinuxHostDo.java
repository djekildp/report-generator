package com.imarchenko.ua.domain;

public class LinuxHostDo {
    private String name;
    private String host;
    private int port;
    private String workDirectory;
    private SshUserDo sshUserDo;

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

    public String getWorkDirectory() {
        return workDirectory;
    }

    public void setWorkDirectory(String workDirectory) {
        this.workDirectory = workDirectory;
    }

    public SshUserDo getSshUserDo() {
        return sshUserDo;
    }

    public void setSshUserDo(SshUserDo sshUserDo) {
        this.sshUserDo = sshUserDo;
    }
}
