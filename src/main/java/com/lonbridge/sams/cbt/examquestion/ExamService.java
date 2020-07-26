package com.lonbridge.sams.cbt.examquestion;

import com.lonbridge.sams.cbt.question.Question;

import java.util.Set;

public interface ExamService {

    Set<ExamQuestion> getQuestions(Long bankId);
    
    Set<ExamQuestion> getQuestionInBanks(String... bankId);

    ExamQuestion getQuestion(long id);


    void submitAnswer(ExamAnswerCmd cmd);
    
    Set< ExamQuestion> convertQuestionsToExamQuestions ( Set< Question > questions );
}
