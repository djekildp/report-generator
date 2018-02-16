package com.imarchenko.ua.dto;

import java.util.List;

public class SshUsersByUserNameResponseDto {
    private List<SshUserNameIdDto> users;

    public List<SshUserNameIdDto> getUsers() {
        return users;
    }

    public void setUsers(List<SshUserNameIdDto> users) {
        this.users = users;
    }


}
