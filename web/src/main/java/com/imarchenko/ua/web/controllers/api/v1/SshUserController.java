package com.imarchenko.ua.web.controllers.api.v1;

import com.imarchenko.ua.bl.CreateSshUser;
import com.imarchenko.ua.bl.SshUserOperation;
import com.imarchenko.ua.bl.SshUsersByUserName;
import com.imarchenko.ua.dal.SshUser;
import com.imarchenko.ua.dto.SshUserRequestDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SshUserController extends ApiController{
    private final static Logger LOG =  Logger.getLogger(SshUserController.class);
    @Autowired
    CreateSshUser createSshUser;

    @Autowired
    SshUsersByUserName sshUsersByUserName;

    @Autowired
    SshUserOperation sshUserOperation;

    @ResponseBody
    @RequestMapping(value = "/sshUser/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSshUser(@RequestBody SshUserRequestDto sshUserRequestDto) {
        return createSshUser.saveUser(sshUserRequestDto);
    }

    @RequestMapping(value = "/sshUser/getUsersByUserName", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getSshUsers(@RequestParam(value = "userName") String userName) {
        return sshUsersByUserName.getUsersByName(userName);
    }
    @RequestMapping(value = "/sshUser/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity deleteSshUser(@RequestParam(value = "id") long id){
        return sshUserOperation.deleteSshUser(id);
    }

}