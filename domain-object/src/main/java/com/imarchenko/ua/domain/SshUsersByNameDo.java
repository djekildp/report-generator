package com.imarchenko.ua.domain;

import java.util.List;

public class SshUsersByNameDo {

    private List<SshUserDo> users;

    public List<SshUserDo> getUsers() {
        return users;
    }

    public void setUsers(List<SshUserDo> users) {
        this.users = users;
    }
}
