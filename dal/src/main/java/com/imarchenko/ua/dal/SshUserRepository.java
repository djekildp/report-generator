package com.imarchenko.ua.dal;

import com.imarchenko.ua.dal.SshUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SshUserRepository extends CrudRepository<SshUser, Long> {
    List<SshUser> findByUserName(String userName);

    void delete(SshUser entity);

}
