package com.midorlo.k9.domain.security;

import java.util.Set;

public interface IRole {

    String COLUMN_ID_NAME                                   = "id";
    String COLUMN_NAME_NAME                                 = "name";
    String JOIN_TABLE_CLEARANCES_NAME                       = "roles_clearances";
    String JOIN_COLUMNS_JOIN_COLUMN_CLEARANCES_NAME         = "role_id";
    String INVERSE_JOIN_COLUMNS_JOIN_COLUMN_CLEARANCES_NAME = "clearances_id";

    Long getId();

    String getName();

    Set<IClearance> getClearances();

    void setId(Long id);

    void setName(String name);

    void setClearances(Set<IClearance> clearances);
}
