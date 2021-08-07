package com.example.demo.module.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByUsername(String name);

    boolean existsByUsername(String username);
}
