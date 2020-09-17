package com.lonbridge.sams.cbt.assessment;

import com.lonbridge.sams.cbt.examquestion.ExamAnswerCmd;
import com.lonbridge.sams.cbt.question.Option;
import com.lonbridge.sams.cbt.question.Question;
import com.lonbridge.sams.cbt.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final QuestionService questionService;

    @Autowired
    public AssessmentServiceImpl ( QuestionService questionService ) {this.questionService = questionService;}

    @Override
    public AssessmentSummary submitAnswer ( List< ExamAnswerCmd > examAnswers ) {
        List< Assessment > assessments = new ArrayList<> (  );
        long totalPoint = 0;

        for ( ExamAnswerCmd examAnswer : examAnswers ) {
            Question question = questionService.getQuestion ( examAnswer.getQuestionId () );
            Assessment assessment = assessAnswer ( question , examAnswer.getAnswer () );
            assessments.add ( assessment );
            for ( Option option : question.getOptions () ) {
                totalPoint += option.getPoint ();
            }
        }
        return new AssessmentSummary (assessments, totalPoint);
    }

    private Assessment assessAnswer ( Question question, String answer ) {
        boolean isAnswerCorrect = false;
        long answerPoint = 0;

        for ( Option option : question.getOptions () ) {
            if ( option.getAnswer ().equalsIgnoreCase ( answer ) ) {
                isAnswerCorrect = option.getCorrect ();
                answerPoint = option.getPoint ();
            }
        }

        Assessment assessment = new Assessment ();
        assessment.setQuestionId ( question.getId () );
        assessment.setQuestion ( question.getDescription () );
        assessment.setCorrect ( isAnswerCorrect );
        assessment.setQuestionPoint ( answerPoint );
        return assessment;
    }
}
