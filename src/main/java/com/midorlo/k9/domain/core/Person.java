package com.midorlo.k9.domain.core;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "persons")
public class Person {

    public static final String COLUMN_FIRSTNAME_NAME = "first_name";
    public static final String COLUMN_LASTNAME_NAME  = "last_name";
    public static final String COLUMN_ID_NAME        = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    protected Long id;

    @Column(name = COLUMN_FIRSTNAME_NAME)
    protected String firstName;

    @Column(name = COLUMN_LASTNAME_NAME)
    protected String lastName;
}
