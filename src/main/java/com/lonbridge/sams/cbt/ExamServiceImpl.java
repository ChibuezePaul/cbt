package com.lonbridge.sams.cbt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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
	
	protected static ExamQuestion convertQuestionToExamQuestion ( Question q ) {
		ExamQuestion examQuestion = new ExamQuestion ();
		examQuestion.setQuestion ( q.getQuestion () );
		Set< ExamAnswerCmd > answers = new HashSet<> ();
		q.getOptions ().forEach ( o -> {
			ExamAnswerCmd answerCmd = new ExamAnswerCmd ();
			answerCmd.setQuestionId ( q.getId () );
			answerCmd.setAnswer ( o.getAnswer () );
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
		log.info("Retrieving exam question with id {}", id);
		Question question = questionService.getQuestion ( id );
		return convertQuestionToExamQuestion(question);
	}
	
	@Override
	public void submitAnswer ( ExamAnswerCmd cmd ) {
		Question question = questionService.getQuestion ( cmd.getQuestionId () );
		Boolean correct = false;
		for(Option o : question.getOptions ()) {
			if(o.getAnswer ().trim ().equals ( cmd.getAnswer ().trim () )){
				log.info ( "Answer chosen: {}", o );
				correct = o.getCorrect ();
			}
		}
		log.info ( "Answer gotten is correct {}", correct );
	}
	
	@Override
	public Set< ExamQuestion > convertQuestionsToExamQuestions ( Set< Question > questions ) {
		return collectExamQuestionsInASet ( questions );
	}
}
