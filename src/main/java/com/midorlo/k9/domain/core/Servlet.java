package com.midorlo.k9.domain.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static com.midorlo.k9.domain.core.property.CoreDomainConstants.*;

@Entity
@Table(name = SERVLETS)

@Getter
@Setter
@NoArgsConstructor

public class Servlet {


    @Id
    @Column(name = PATH,
            nullable = false,
            unique = true
    )
    private String path;

    @ManyToOne(optional = false,
               cascade = {
                       CascadeType.DETACH,
                       CascadeType.MERGE
               }
    )
    @JoinColumn(name = SERVLETS_MODULES_MODULES_MODULE,
                nullable = false
    )
    protected Module module;
}
