package com.imarchenko.ua.web.controllers.api.v1;

import com.imarchenko.ua.bl.SshUserOperation;
import com.imarchenko.ua.dto.SshUserRequestDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SshUserControllerV1 extends ApiControllerV1 {
    private final static Logger LOG =  Logger.getLogger(SshUserControllerV1.class);

    @Autowired
    SshUserOperation sshUserOperation;

    @ResponseBody
    @RequestMapping(value = "/sshUser/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createSshUser(@RequestBody SshUserRequestDto sshUserRequestDto) {
        return sshUserOperation.saveUser(sshUserRequestDto);
    }

    @RequestMapping(value = "/sshUser/getUsersByUserName", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getSshUsers(@RequestParam(value = "userName") String userName) {
        return sshUserOperation.getUsersByName(userName);
    }

    @RequestMapping(value = "/sshUser/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity deleteSshUser(@RequestParam(value = "id") long id){
        return sshUserOperation.deleteSshUser(id);
    }

    @RequestMapping(value = "/sshUser/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updateSshUser(@RequestBody SshUserRequestDto sshUserRequestDto){
        return sshUserOperation.updateSshUser(sshUserRequestDto);
    }

}