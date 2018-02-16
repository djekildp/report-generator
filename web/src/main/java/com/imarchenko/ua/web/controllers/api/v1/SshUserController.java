package com.imarchenko.ua.web.controllers.api.v1;

import com.imarchenko.ua.bl.CreateSshUser;
import com.imarchenko.ua.bl.SshUsersByUserName;
import com.imarchenko.ua.bl.UpdateSshUser;
import com.imarchenko.ua.dto.SshUserRequestDto;
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

    @Autowired
    SshUsersByUserName sshUsersByUserName;

    @ResponseBody
    @RequestMapping(value = "/sshUser/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSshUser(@RequestBody SshUserRequestDto sshUserRequestDto) {
        return createSshUser.saveUser(sshUserRequestDto);
    }

    @RequestMapping(value = "/sshUser/getUsersByUserName", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getSshUser(@RequestParam(value = "userName") String userName) {
        return sshUsersByUserName.getUsersByName(userName);
    }

}