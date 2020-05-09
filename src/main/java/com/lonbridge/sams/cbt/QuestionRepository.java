package com.lonbridge.sams.cbt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    Set<Question> findByBankId(String bankId);
    Set<Question> findByBankIdIn(String... bankId);
    Question findByOptionsContains(String option);
}
