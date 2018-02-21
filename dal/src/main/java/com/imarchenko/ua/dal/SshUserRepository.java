package com.imarchenko.ua.dal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface SshUserRepository extends CrudRepository<SshUser, Long> {
    List<SshUser> findByUserName(String userName);
    void delete(SshUser entity);
}