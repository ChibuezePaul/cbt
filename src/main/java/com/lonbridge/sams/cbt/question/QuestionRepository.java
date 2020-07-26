package com.lonbridge.sams.cbt.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    Set<Question> findByBankId(Long bankId);
    Set<Question> findByBankIdIn(String... bankId);
    Question findByOptionsContains(String option);
}
