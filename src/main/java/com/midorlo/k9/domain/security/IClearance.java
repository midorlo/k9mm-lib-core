package com.midorlo.k9.domain.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public interface IClearance {

    String COLUMN_ID_NAME   = "id";
    String COLUMN_NAME_NAME = "name";

    @Column(name = COLUMN_NAME_NAME)
    String getName();

    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    Long getId();

    void setId(Long id);

    void setName(String name);
}
