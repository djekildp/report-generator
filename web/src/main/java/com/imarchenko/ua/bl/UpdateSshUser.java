package com.imarchenko.ua.bl;

import com.imarchenko.ua.dal.SshUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateSshUser {
    @Autowired
    private SshUserRepository sshUserRepository;

    public ResponseEntity getUsersByName(String userName) {

        return null;
    }
}
