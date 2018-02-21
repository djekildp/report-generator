package com.imarchenko.ua.service.mapper;

import com.imarchenko.ua.dal.SshUser;
import com.imarchenko.ua.domain.SshUserDo;
import com.imarchenko.ua.dto.SshUserNameIdDto;
import com.imarchenko.ua.dto.SshUserRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SshUserMapper {

    SshUserDo toSshUserDo (SshUserRequestDto sshUserRequestDto);

    SshUserRequestDto toSshUserRequestDto(SshUserDo sshUserDo);
    SshUser toSshUser (SshUserDo sshUserDo);
    SshUserDo toSshUserDoFromSshUser(SshUser sshUser);
    SshUserNameIdDto toSshUserNameIdDtoFromSshUserDo (SshUserDo sshUserDo);
}
