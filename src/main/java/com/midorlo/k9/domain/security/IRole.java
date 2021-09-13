package com.midorlo.k9.domain.security;

import javax.persistence.*;
import java.util.Set;

@Entity
public interface IRole {

    String COLUMN_ID_NAME                                   = "id";
    String COLUMN_NAME_NAME                                 = "name";
    String JOIN_TABLE_CLEARANCES_NAME                       = "roles_clearances";
    String JOIN_COLUMNS_JOIN_COLUMN_CLEARANCES_NAME         = "role_id";
    String INVERSE_JOIN_COLUMNS_JOIN_COLUMN_CLEARANCES_NAME = "clearances_id";

    @Id
    @Column(name = COLUMN_ID_NAME, nullable = false)
    Long getId();

    @Column(name = COLUMN_NAME_NAME)
    String getName();

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.DETACH })
    @JoinTable(name = JOIN_TABLE_CLEARANCES_NAME,
               joinColumns = @JoinColumn(name = JOIN_COLUMNS_JOIN_COLUMN_CLEARANCES_NAME),
               inverseJoinColumns = @JoinColumn(name = INVERSE_JOIN_COLUMNS_JOIN_COLUMN_CLEARANCES_NAME))
    Set<IClearance> getClearances();

    void setId(Long id);

    void setName(String name);

    void setClearances(Set<IClearance> clearances);
}
