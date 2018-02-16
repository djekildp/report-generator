package com.imarchenko.ua.bl;

import com.imarchenko.ua.dal.SshUser;
import com.imarchenko.ua.dal.SshUserRepository;
import com.imarchenko.ua.dto.SshUserResponseDto;
import com.imarchenko.ua.service.mapper.SshUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class SshUserOperation {
    private static final String USER_NOT_FIND_MESSAGE = "User not found.";
    private static final String SUCCESS_DELETE_MESSAGE = "User was deleted.";
    @Autowired
    private SshUserRepository sshUserRepository;
    @Autowired
    private SshUserMapper sshUserMapper;

    public ResponseEntity deleteSshUser(long id) {
        SshUser sshUser = sshUserRepository.findOne(id);
        if(sshUser == null){
            return createDeleteUserNotFindResponseEntity(sshUser);
        }
        sshUserRepository.delete(sshUser);
        return createDeleteSuccessResponseEntity(sshUser);
    }

    private ResponseEntity createDeleteUserNotFindResponseEntity(SshUser sshUser) {
        SshUserResponseDto sshUserResponseDto = new SshUserResponseDto();
        sshUserResponseDto.setErrorMessage(USER_NOT_FIND_MESSAGE);
        sshUserResponseDto.setStatus(SshUserResponseDto.Status.NOT_FOUND);
        return new ResponseEntity<SshUserResponseDto>(sshUserResponseDto, HttpStatus.OK);
    }

    private ResponseEntity createDeleteSuccessResponseEntity(SshUser sshUser) {
        SshUserResponseDto sshUserResponseDto = new SshUserResponseDto();
        sshUserResponseDto.setUserName(sshUser.getUserName());
        sshUserResponseDto.setMessage(SUCCESS_DELETE_MESSAGE);
        sshUserResponseDto.setStatus(SshUserResponseDto.Status.DELETED);
        return new ResponseEntity<SshUserResponseDto>(sshUserResponseDto, HttpStatus.OK);
    }
}
