package com.midorlo.k9.repository.security;

import com.midorlo.k9.domain.security.IAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<IAccount, Long> {

    Optional<IAccount> getSystemAccount();


}
