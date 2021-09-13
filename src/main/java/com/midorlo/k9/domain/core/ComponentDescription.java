package com.midorlo.k9.domain.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ComponentDescription {

    @Id
    @Column(name = COLUMN_NAME_NAME, nullable = false, updatable = false)
    protected String name;

    public static final String COLUMN_NAME_NAME = "name";

    public String getName()          {return name;}

    public void setName(String name) {this.name = name;}
}
