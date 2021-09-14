package com.midorlo.k9.domain.security;

public interface IAccount {

    String COLUMN_ID_NAME            = "id";
    String COLUMN_LOGIN_NAME         = "login";
    String COLUMN_PASSWORD_HASH_NAME = "password_hash";

    Long getId();

    String getLogin();

    String getPasswordHash();

    void setLogin(String login);

    void setPasswordHash(String passwordHash);

    void setId(Long id);
}
