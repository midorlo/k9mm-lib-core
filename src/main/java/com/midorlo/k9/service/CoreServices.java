package com.midorlo.k9.service;

import com.midorlo.k9.domain.security.IAccount;
import com.midorlo.k9.service.security.ISecurityServices;
import org.springframework.stereotype.Service;

/**
 * Services that should be available to any component.
 */
@Service
public class CoreServices {

    /**
     * Require a security service implementation globally.
     */
    private final ISecurityServices iSecurityServices;

    public CoreServices(ISecurityServices iSecurityServices) {
        this.iSecurityServices = iSecurityServices;
    }

    /**
     * Gets the application's system account.
     *
     * @return system account.
     */
    public IAccount getSystemAccount() {
        return iSecurityServices.getAccount(1L);
    }
}
