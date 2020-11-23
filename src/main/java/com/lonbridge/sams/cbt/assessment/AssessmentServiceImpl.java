package com.lonbridge.sams.cbt.assessment;

import com.lonbridge.sams.cbt.examquestion.ExamAnswerCmd;
import com.lonbridge.sams.cbt.question.Option;
import com.lonbridge.sams.cbt.question.Question;
import com.lonbridge.sams.cbt.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final QuestionService questionService;

    @Autowired
    public AssessmentServiceImpl ( QuestionService questionService ) {this.questionService = questionService;}

    @Override
    public AssessmentSummary submitAnswer ( List< ExamAnswerCmd > examAnswers ) {
        List< Assessment > assessments = new ArrayList<> (  );
        final long[] totalPoint = { 0 };
        
        Map< Long, String > uniqueExamAnswers = new HashMap<> ();
        examAnswers.forEach ( examAnswerCmd -> uniqueExamAnswers.put ( examAnswerCmd.getQuestionId () , examAnswerCmd.getAnswer () ));
    
        uniqueExamAnswers.forEach ( (questionId, answer) -> {
            Question question = questionService.getQuestion (questionId);
            Assessment assessment = assessAnswer ( question , answer );
            assessments.add ( assessment );
			totalPoint[ 0 ] += assessment.getQuestionPoint ();
        } );
        return new AssessmentSummary (assessments, totalPoint[0]);
    }

    private Assessment assessAnswer ( Question question, String answer ) {
        boolean isAnswerCorrect = false;
        long answerPoint = 0;
        if(answer != null) {
            if ( question.isMultipleEntry () ) {
				List< String > answers = Stream.of ( answer.toLowerCase().split ( "," ) )
					  .map ( String :: trim )
					  .collect ( Collectors.toList () );
                for ( Option option : question.getOptions () ) {
                    if ( answers.contains ( option.getAnswer ().toLowerCase () ) ) {
                        isAnswerCorrect = option.getCorrect ();
                        answerPoint += option.getPoint ();
                    }
                }
            }
            else {
                for ( Option option : question.getOptions () ) {
                    if ( option.getAnswer ().trim ().equalsIgnoreCase ( answer.trim () ) ) {
                        isAnswerCorrect = option.getCorrect ();
                        answerPoint = option.getPoint ();
                    }
                }
            }
        }

        Assessment assessment = new Assessment ();
        assessment.setQuestionId ( question.getId () );
        assessment.setQuestion ( question.getDescription () );
        assessment.setAnswer ( answer );
        assessment.setCorrect ( isAnswerCorrect );
        assessment.setQuestionPoint ( answerPoint );
        return assessment;
    }
}
