package com.imarchenko.ua.service.mapper;

import com.imarchenko.ua.dal.SshUser;
import com.imarchenko.ua.domain.CreateSshUserDo;
import com.imarchenko.ua.dto.SshUserRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SshUserMapper {

    CreateSshUserDo toCreateSshUserDo (SshUserRequestDto sshUserRequestDto);
    SshUserRequestDto toSshUserRequestDto(CreateSshUserDo createSshUserDo);
    SshUser toSshUser (CreateSshUserDo createSshUserDo);
}
