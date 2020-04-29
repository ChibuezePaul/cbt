package com.lonbridge.sams.cbt;

import java.util.Set;

public interface ExamService {

    Set<ExamQuestion> getQuestions(String bankId);

    ExamQuestion getQuestion(long id);


    void submitAnswer(ExamAnswerCmd cmd);
}
