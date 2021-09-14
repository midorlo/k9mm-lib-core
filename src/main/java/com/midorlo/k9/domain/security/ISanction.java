package com.midorlo.k9.domain.security;

public interface ISanction {

    String COLUMN_ID_NAME   = "id";
    String COLUMN_NAME_NAME = "name";

    String getName();

    Long getId();

    void setId(Long id);

    void setName(String name);
}
