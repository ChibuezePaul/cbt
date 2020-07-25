package com.lonbridge.sams.cbt.examquestion;

import com.lonbridge.sams.cbt.assessment.Assessment;
import com.lonbridge.sams.cbt.question.Question;

import java.util.List;
import java.util.Set;

public interface ExamService {

    Set<ExamQuestion> getQuestions(String bankId);
    
    Set<ExamQuestion> getQuestionInBanks(String... bankId);

    ExamQuestion getQuestion(long id);
    
    Set< ExamQuestion> convertQuestionsToExamQuestions ( Set< Question > questions );
}
