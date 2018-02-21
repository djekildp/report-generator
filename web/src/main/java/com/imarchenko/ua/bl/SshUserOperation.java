package com.imarchenko.ua.bl;

import com.imarchenko.ua.dal.SshUser;
import com.imarchenko.ua.dal.SshUserRepository;
import com.imarchenko.ua.domain.SshUserDo;
import com.imarchenko.ua.dto.SshUserNameIdDto;
import com.imarchenko.ua.dto.SshUserRequestDto;
import com.imarchenko.ua.dto.SshUserResponseDto;
import com.imarchenko.ua.dto.SshUsersByUserNameResponseDto;
import com.imarchenko.ua.service.mapper.SshUserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Component
@Transactional
public class SshUserOperation {
    private final static Logger LOG =  Logger.getLogger(SshUserOperation.class);
    private final static String SUCCESS_MESSAGE = "User was created.";
    private final static String BAD_REQUEST_MESSAGE = "The params are not correct.";
    private final static String USER_NOT_FIND_MESSAGE = "User not found.";
    private final static String SUCCESS_DELETE_MESSAGE = "User was deleted.";
    private final static String SUCCESS_UPDATE_MESSAGE = "User was updated.";

    @Autowired
    private SshUserRepository sshUserRepository;
    @Autowired
    private SshUserMapper sshUserMapper;

    public ResponseEntity deleteSshUser(long id) {
        SshUser sshUser = sshUserRepository.findOne(id);
        SshUserDo sshUserDo = sshUserMapper.toSshUserDoFromSshUser(sshUser);
        if(sshUser == null){
            return buildResponseEntity(sshUserDo, null,USER_NOT_FIND_MESSAGE, SshUserResponseDto.Status.NOT_FOUND);
        }
        sshUserRepository.delete(sshUser);
        return buildResponseEntity(sshUserDo, SUCCESS_DELETE_MESSAGE,null, SshUserResponseDto.Status.DELETED);
    }

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

    public ResponseEntity saveUser(SshUserRequestDto sshUserRequestDto){
        SshUserDo sshUserDo = sshUserMapper.toSshUserDo(sshUserRequestDto);
        if(isUserAndPassword(sshUserDo) || isUserAndPrvkey(sshUserDo)){
            SshUser sshUser = sshUserMapper.toSshUser(sshUserDo);
            sshUserRepository.save(sshUser);
            return buildResponseEntity(sshUserDo, SUCCESS_MESSAGE, null, SshUserResponseDto.Status.CREATED);
        }
        return buildResponseEntity(sshUserDo,null,BAD_REQUEST_MESSAGE, SshUserResponseDto.Status.BAD_REQUEST);
    }

    public ResponseEntity updateSshUser(SshUserRequestDto sshUserRequestDto){
        SshUserDo sshUserDo = sshUserMapper.toSshUserDo(sshUserRequestDto);
        if(sshUserDo.getId() == null){
            throw new IllegalArgumentException("Parameter \"id\" must exist!");
        }
        if(isUserAndPassword(sshUserDo) || isUserAndPrvkey(sshUserDo)){
            SshUser sshUser = sshUserMapper.toSshUser(sshUserDo);
            sshUserRepository.save(sshUser);
        }
        return buildResponseEntity(sshUserDo, SUCCESS_UPDATE_MESSAGE,null, SshUserResponseDto.Status.UPDATED);
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

    private ResponseEntity buildResponseEntity(SshUserDo sshUserDo, String message, String errorMessage, SshUserResponseDto.Status status){
        SshUserResponseDto sshUserResponseDto = new SshUserResponseDto();
        sshUserResponseDto.setUserName(sshUserDo.getUserName());
        sshUserResponseDto.setMessage(message);
        sshUserResponseDto.setErrorMessage(errorMessage);
        sshUserResponseDto.setStatus(status);
        switch (status){
            case CREATED: return new ResponseEntity<SshUserResponseDto>(sshUserResponseDto, HttpStatus.CREATED);
            case BAD_REQUEST: return new ResponseEntity<SshUserResponseDto>(sshUserResponseDto, HttpStatus.BAD_REQUEST);
            default: return new ResponseEntity<SshUserResponseDto>(sshUserResponseDto, HttpStatus.OK);
        }
    }
}
