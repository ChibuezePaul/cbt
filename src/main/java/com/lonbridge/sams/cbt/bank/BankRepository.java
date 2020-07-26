package com.lonbridge.sams.cbt.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Set<Bank> findByIdIn(Long... bankId);

}
