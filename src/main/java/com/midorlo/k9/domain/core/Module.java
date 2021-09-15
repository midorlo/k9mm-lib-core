package com.midorlo.k9.domain.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static com.midorlo.k9.domain.core.property.CoreDomainConstants.MODULES;
import static com.midorlo.k9.domain.core.property.CoreDomainConstants.NAME;

@Entity
@Table(name = MODULES)

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Module {

    @Id
    @Column(name = NAME,
            nullable = false,
            updatable = false
    )
    protected String name;

    @Column
    protected boolean initialized;
}
