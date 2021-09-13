package com.midorlo.k9.domain.security;

import javax.persistence.*;

@Entity
public interface IAccount {

    String COLUMN_ID_NAME            = "id";
    String COLUMN_LOGIN_NAME         = "login";
    String COLUMN_PASSWORD_HASH_NAME = "password_hash";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    Long getId();

    String getLogin();

    String getPasswordHash();

    void setLogin(String login);

    void setPasswordHash(String passwordHash);

    void setId(Long id);
}
