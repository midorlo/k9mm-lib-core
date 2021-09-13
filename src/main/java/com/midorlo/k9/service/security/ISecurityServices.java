package com.midorlo.k9.service.security;

import com.midorlo.k9.domain.security.IAccount;
import org.springframework.stereotype.Service;

/**
 * Describe the minimum capabilities of a Security Services implementation.
 */
@Service
public interface ISecurityServices {

    /**
     * Gets the account with given id.
     *
     * @param id id.
     * @return account.
     */
    IAccount getAccount(Long id);
}
