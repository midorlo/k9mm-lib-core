package com.midorlo.k9.domain.hr;

import javax.persistence.*;

@Entity
public class Person {

    public static final String COLUMN_FIRSTNAME_NAME = "first_name";
    public static final String COLUMN_LASTNAME_NAME  = "last_name";
    public static final String COLUMN_ID_NAME        = "id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = COLUMN_ID_NAME, nullable = false)
    protected Long id;

    @Column(name = COLUMN_FIRSTNAME_NAME)
    protected String firstName;

    @Column(name = COLUMN_LASTNAME_NAME)
    protected String lastName;

    public Long getId()                        {return id;}

    public void setId(Long id)                 {this.id = id;}

    public String getLastName()                {return lastName;}

    public void setLastName(String lastName)   {this.lastName = lastName;}

    public String getFirstName()               {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}
}
