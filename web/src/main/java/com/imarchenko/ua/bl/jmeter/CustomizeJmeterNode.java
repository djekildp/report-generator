package com.imarchenko.ua.bl.jmeter;

import com.imarchenko.ua.domain.LinuxHostDo;
import com.imarchenko.ua.domain.SshUserDo;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.log4j.Logger;

import java.util.Properties;

public class CustomizeJmeterNode {
    private final static Logger LOG =  Logger.getLogger(CustomizeJmeterNode.class);
    private JSch jSch = new JSch();
    private Session session;
    private LinuxHostDo linuxHostDo;
    private SshUserDo sshUserDo;

    public CustomizeJmeterNode(LinuxHostDo linuxHostDo) {
        this.linuxHostDo = linuxHostDo;
        sshUserDo = linuxHostDo.getSshUserDo();
    }

    public void setUp(){
        /*
        * create ssh connection
        * set up jmeter server
        * run jmeter server
        *
        * */
        setUpSshConnection();
        createSession();
        setUpJmeter();
    }

    private void setUpJmeter() {
        try {
            Channel sftpchannel = session.openChannel("sftp");
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    private void createSession() {
        String userName = sshUserDo.getUserName();
        String password = sshUserDo.getPassword();
        String host = linuxHostDo.getHost();
        int port = linuxHostDo.getPort();
        try {
            session = jSch.getSession(userName, host , port);
        } catch (JSchException e) {
            e.printStackTrace();
        }
        if(!isNull(password)){
            session.setPassword(password);
        }
    }

    private void setUpSshConnection() {
        String prvkey = sshUserDo.getPrvkey();
        String phrase = sshUserDo.getPhrase();

        if(!isNull(prvkey) && !prvkey.isEmpty()){
            try {
                if(!isNull(phrase) && !phrase.isEmpty()){
                    jSch.addIdentity(prvkey, phrase);
                }else{
                    jSch.addIdentity(prvkey);
                }
            } catch (JSchException e) {
                LOG.error("Error set prvkey to JSch!", e);
            }
        }
    }

    private <T> boolean isNull(T t){
        return t == null;
    }
}
