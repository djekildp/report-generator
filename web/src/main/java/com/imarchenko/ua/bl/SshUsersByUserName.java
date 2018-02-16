package com.imarchenko.ua.bl;

import com.imarchenko.ua.dal.SshUser;
import com.imarchenko.ua.dal.SshUserRepository;
import com.imarchenko.ua.dto.SshUserNameIdDto;
import com.imarchenko.ua.dto.SshUsersByUserNameResponseDto;
import com.imarchenko.ua.service.mapper.SshUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SshUsersByUserName {
    @Autowired
    private SshUserRepository sshUserRepository;
    @Autowired
    private SshUserMapper sshUserMapper;
    public ResponseEntity<SshUsersByUserNameResponseDto> getUsersByName(String userName) {
        List<SshUser> usersSshUser = sshUserRepository.findByUserName(userName);
        SshUsersByUserNameResponseDto responseDto = new SshUsersByUserNameResponseDto();
        List<SshUserNameIdDto> usersSshUserNameIdDto = new ArrayList<>();
        for(SshUser sshUser : usersSshUser){
            usersSshUserNameIdDto.add(sshUserMapper.toSshUserNameIdDtoFromSshUserDo(sshUserMapper.toSshUserDoFromSshUser(sshUser)));
        }
        responseDto.setUsers(usersSshUserNameIdDto);
        return new ResponseEntity<SshUsersByUserNameResponseDto>(responseDto, HttpStatus.OK);
    }
}
