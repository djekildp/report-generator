package com.imarchenko.ua.bl;

import com.imarchenko.ua.dal.SshUser;
import com.imarchenko.ua.dal.SshUserRepository;
import com.imarchenko.ua.domain.SshUserDo;
import com.imarchenko.ua.dto.SshUserRequestDto;
import com.imarchenko.ua.dto.SshUserResponseDto;
import com.imarchenko.ua.service.mapper.SshUserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class CreateSshUser {
    public final static String SUCCESS_MESSAGE = "User was created.";
    public final static String BAD_REQUEST_MESSAGE = "The params are not correct.";
    private final static Logger LOG =  Logger.getLogger(CreateSshUser.class);

    @Autowired
    private SshUserRepository sshUserRepository;
    @Autowired
    private SshUserMapper sshUserMapper;

    public ResponseEntity saveUser(SshUserRequestDto sshUserRequestDto){
        SshUserDo sshUserDo = sshUserMapper.toSshUserDo(sshUserRequestDto);
        LOG.info(sshUserDo);
        if(isUserAndPassword(sshUserDo)){
            SshUser sshUser = sshUserMapper.toSshUser(sshUserDo);
            sshUserRepository.save(sshUser);
            return createSuccessResponseEntity(sshUserDo);
        }
        if(isUserAndPrvkey(sshUserDo)){
            SshUser sshUser = sshUserMapper.toSshUser(sshUserDo);
            sshUserRepository.save(sshUser);
            return createSuccessResponseEntity(sshUserDo);
        }
        return createErrorResponseEntity(sshUserDo);
    }

    private boolean isUserAndPrvkey(SshUserDo sshUserDo) {
        return (!isNullOrEmpty(sshUserDo.getUserName())
                && isNullOrEmpty(sshUserDo.getPassword())
                && !isNullOrEmpty(sshUserDo.getPrvkey()));
    }

    private boolean isUserAndPassword(SshUserDo sshUserDo) {
        return (!isNullOrEmpty(sshUserDo.getUserName()) && !isNullOrEmpty(sshUserDo.getPassword()) &&
                (isNullOrEmpty(sshUserDo.getPrvkey()) && isNullOrEmpty(sshUserDo.getPhrase())));
    }
    private boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    private ResponseEntity<SshUserResponseDto> createSuccessResponseEntity(SshUserDo sshUserDo){
        SshUserResponseDto sshUserResponseDto = new SshUserResponseDto();
        sshUserResponseDto.setUserName(sshUserDo.getUserName());
        sshUserResponseDto.setMessage(SUCCESS_MESSAGE);
        sshUserResponseDto.setStatus(SshUserResponseDto.Status.CREATED);
        return new ResponseEntity<SshUserResponseDto>(sshUserResponseDto, HttpStatus.CREATED);
    }

    private ResponseEntity<SshUserResponseDto> createErrorResponseEntity(SshUserDo sshUserDo){
        SshUserResponseDto sshUserResponseDto = new SshUserResponseDto();
        sshUserResponseDto.setUserName(sshUserDo.getUserName());
        sshUserResponseDto.setErrorMessage(BAD_REQUEST_MESSAGE);
        sshUserResponseDto.setStatus(SshUserResponseDto.Status.ERROR);
        return new ResponseEntity<SshUserResponseDto>(sshUserResponseDto, HttpStatus.CREATED);
    }
}
