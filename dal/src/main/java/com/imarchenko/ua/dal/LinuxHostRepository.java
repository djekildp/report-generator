package com.imarchenko.ua.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LinuxHostRepository extends CrudRepository<LinuxHostEntity, Long> {
    List<LinuxHostEntity> findByName(String name);
    List<LinuxHostEntity> findByHost(String host);
}
