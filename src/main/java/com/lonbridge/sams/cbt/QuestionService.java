package com.lonbridge.sams.cbt;

import java.util.Set;

public interface QuestionService {

    Set<Question> getQuestions(String bankId);

    Question getQuestion(long id);

    void deleteQuestion(long id);

    Question addQuestion(NewQuestionCmd cmd);

    Question updateQuestion(UpdateQuestionCmd cmd);

}
