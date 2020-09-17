package com.lonbridge.sams.cbt.bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {

    Set<Bank> findByIdIn(List<String> bankId);
}
