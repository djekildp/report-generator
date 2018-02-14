package com.imarchenko.ua.web.controllers.api.v1;

import com.imarchenko.ua.bl.CreateSshUser;
import com.imarchenko.ua.dal.SshUser;
import com.imarchenko.ua.dal.SshUserRepository;
import com.imarchenko.ua.domain.CreateSshUserDo;
import com.imarchenko.ua.dto.SshUserRequestDto;
import com.imarchenko.ua.service.mapper.SshUserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/v1/")
public class SshUserController {
    private final static Logger LOG =  Logger.getLogger(SshUserController.class);
    @Autowired
    CreateSshUser createSshUser;

    @ResponseBody
    @RequestMapping(value = "/sshUser/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSshUser(@RequestBody SshUserRequestDto sshUserRequestDto) {
        return createSshUser.saveUser(sshUserRequestDto);
    }

    @ResponseBody
    @RequestMapping(value = "/sshUser/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateSshUser(@RequestBody SshUserRequestDto createSshUserRequestDto) {

        return new ResponseEntity<SshUserRequestDto>(createSshUserRequestDto, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/sshUser/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteSshUser(@RequestBody SshUserRequestDto createSshUserRequestDto) {
        return new ResponseEntity<SshUserRequestDto>(createSshUserRequestDto, HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/sshUser/getUsersByUserName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<SshUser> getSshUser(@RequestParam(value = "userName") String userName) {
//        List<SshUser> sshUserList = sshUserRepository.findByUserName(userName);
//        return sshUserList;
//    }

}