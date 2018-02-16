package com.imarchenko.ua.dal;

import javax.persistence.*;
import java.util.Set;
@Entity
public class GroupOfLinuxHostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany
    @Column(unique = true)
    private Set<LinuxHostEntity> entities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LinuxHostEntity> getEntities() {
        return entities;
    }

    public void setEntities(Set<LinuxHostEntity> entities) {
        this.entities = entities;
    }
}
