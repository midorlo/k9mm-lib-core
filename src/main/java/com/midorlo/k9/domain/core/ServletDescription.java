package com.midorlo.k9.domain.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "servlet_descriptions")
@Getter
@Setter
@RequiredArgsConstructor
public class ServletDescription {

    public static final String COLUMN_ID_NAME = "id";

    public ServletDescription(String path) {
        this.path = path;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    protected Long id;

    @Column(name = "path", nullable = false, unique = true, length = 128)
    private String path;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE }, optional = false)
    @JoinColumn(name = "component_description_name", nullable = false)
    protected ComponentDescription componentDescription;
}
