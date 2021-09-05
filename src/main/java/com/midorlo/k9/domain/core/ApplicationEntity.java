package com.midorlo.k9.domain.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public abstract class ApplicationEntity {

    public ApplicationEntity(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "id", nullable = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull
    private String name;

    @Override
    public boolean equals(Object o) {
        return (this == o) || (
                o != null
                        && getClass().equals(o.getClass())
                        && getId() != null
                        && getName() != null
                        && getId().equals(((ApplicationEntity) o).getId())
                        && getName().equals(((ApplicationEntity) o).getName())
        );
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
