package com.lonbridge.sams.cbt.question;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    Set<Question> getQuestions(String bankId);
    
    Set<Question> getQuestionInBanks(String... bankId);

    Question getQuestion(long id);

    void deleteQuestion(long id);

    Question addQuestion(NewQuestionCmd cmd);

    Question updateQuestion(UpdateQuestionCmd cmd);
    
    List< Question > addQuestions ( List< NewQuestionCmd> cmds );
}
