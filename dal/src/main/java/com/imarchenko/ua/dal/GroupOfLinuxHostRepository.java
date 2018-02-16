package com.imarchenko.ua.dal;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupOfLinuxHostRepository extends CrudRepository<GroupOfLinuxHostEntity, Long> {
    List<GroupOfLinuxHostEntity> findByName(String name);
}
