package com.midorlo.k9.domain.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "servlet_paths")
@Getter
@Setter
@RequiredArgsConstructor
public class ServletPath {

    public static final String COLUMN_ID_NAME = "id";

    public ServletPath(String path) {
        this.path = path;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    protected Long id;

    @Column(name = "path", nullable = false, unique = true, length = 128)
    private String path;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServletPath servletPath = (ServletPath) o;
        return Objects.equals(getId(), servletPath.getId());
    }
}
