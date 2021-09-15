package com.midorlo.k9.domain.security;

import com.midorlo.k9.domain.hr.Person;

/**
 * Describe an Account, but leave the actual implementation to an interchangeable module.
 */
public interface IAccount {
    Long getId();

    String getDisplayName();

    Person getOwner();
}
