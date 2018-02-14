package com.imarchenko.ua.bl;

import com.imarchenko.ua.dal.SshUser;
import com.imarchenko.ua.dal.SshUserRepository;
import com.imarchenko.ua.domain.CreateSshUserDo;
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
        CreateSshUserDo createSshUserDo = sshUserMapper.toCreateSshUserDo(sshUserRequestDto);
        LOG.info(createSshUserDo);
        if(isUserAndPassword(createSshUserDo)){
            SshUser sshUser = sshUserMapper.toSshUser(createSshUserDo);
            sshUserRepository.save(sshUser);
            return createSuccessResponseEntity(createSshUserDo);
        }
        if(isUserAndPrvkey(createSshUserDo)){
            SshUser sshUser = sshUserMapper.toSshUser(createSshUserDo);
            sshUserRepository.save(sshUser);
            return createSuccessResponseEntity(createSshUserDo);
        }
        return createErrorResponseEntity(createSshUserDo);
    }

    private boolean isUserAndPrvkey(CreateSshUserDo createSshUserDo) {
        return (!isNullOrEmpty(createSshUserDo.getUserName())
                && isNullOrEmpty(createSshUserDo.getPassword())
                && !isNullOrEmpty(createSshUserDo.getPrvkey()));
    }

    private boolean isUserAndPassword(CreateSshUserDo createSshUserDo) {
        return (!isNullOrEmpty(createSshUserDo.getUserName()) && !isNullOrEmpty(createSshUserDo.getPassword()) &&
                (isNullOrEmpty(createSshUserDo.getPrvkey()) && isNullOrEmpty(createSshUserDo.getPhrase())));
    }
    private boolean isNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }
    private ResponseEntity<SshUserResponseDto> createSuccessResponseEntity(CreateSshUserDo createSshUserDo){
        SshUserResponseDto sshUserResponseDto = new SshUserResponseDto();
        sshUserResponseDto.setUserName(createSshUserDo.getUserName());
        sshUserResponseDto.setMessage(SUCCESS_MESSAGE);
        sshUserResponseDto.setStatus(SshUserResponseDto.Status.CREATED);
        return new ResponseEntity<SshUserResponseDto>(sshUserResponseDto, HttpStatus.CREATED);
    }

    private ResponseEntity<SshUserResponseDto> createErrorResponseEntity(CreateSshUserDo createSshUserDo){
        SshUserResponseDto sshUserResponseDto = new SshUserResponseDto();
        sshUserResponseDto.setUserName(createSshUserDo.getUserName());
        sshUserResponseDto.setErrorMessage(BAD_REQUEST_MESSAGE);
        sshUserResponseDto.setStatus(SshUserResponseDto.Status.ERROR);
        return new ResponseEntity<SshUserResponseDto>(sshUserResponseDto, HttpStatus.CREATED);
    }
}
