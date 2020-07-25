package com.lonbridge.sams.cbt.examquestion;

import com.lonbridge.sams.cbt.assessment.Assessment;
import com.lonbridge.sams.cbt.assessment.AssessmentSummary;
import com.lonbridge.sams.cbt.question.Option;
import com.lonbridge.sams.cbt.question.Question;
import com.lonbridge.sams.cbt.question.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExamServiceImpl implements ExamService {
	
	private final QuestionService questionService;
	
	@Autowired
	public ExamServiceImpl ( QuestionService questionService ) {this.questionService = questionService;}
	
	private Set< ExamQuestion > collectExamQuestionsInASet ( Set< Question > questions ) {
		return questions.stream ()
			  .map ( ExamServiceImpl :: convertQuestionToExamQuestion )
			  .collect ( Collectors.toSet () );
	}
	
	protected static ExamQuestion convertQuestionToExamQuestion ( Question question ) {
		ExamQuestion examQuestion = new ExamQuestion ();
		examQuestion.setDescription ( question.getDescription () );
		Set< ExamAnswerCmd > answers = new HashSet<> ();
		question.getOptions ().forEach ( option -> {
			ExamAnswerCmd answerCmd = new ExamAnswerCmd ();
			answerCmd.setQuestionId ( question.getId () );
			answerCmd.setAnswer ( option.getAnswer () );
			answers.add ( answerCmd );
		} );
		examQuestion.setAnswers ( answers );
		return examQuestion;
	}
	
	@Override
	public Set< ExamQuestion > getQuestions ( String bankId ) {
		log.info("Retrieving exam questions from bank {}", bankId);
		Set< Question > questions = questionService.getQuestions ( bankId );
		return collectExamQuestionsInASet ( questions );
	}
	
	@Override
	public Set< ExamQuestion > getQuestionInBanks ( String... bankId ) {
		log.info("Retrieving exam questions banks  {}", Arrays.toString ( bankId ) );
		Set< Question > questions = questionService.getQuestionInBanks ( bankId );
		return collectExamQuestionsInASet ( questions );
	}
	
	@Override
	public ExamQuestion getQuestion ( long id ) {
		log.info("Retrieving exam description with id {}", id);
		Question question = questionService.getQuestion ( id );
		return convertQuestionToExamQuestion(question);
	}
	
	
	@Override
	public Set< ExamQuestion > convertQuestionsToExamQuestions ( Set< Question > questions ) {
		return collectExamQuestionsInASet ( questions );
	}
}
