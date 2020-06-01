package com.lonbridge.sams.cbt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExamServiceImplTest {
	
	@Mock
	private QuestionService questionService;
	
	@Mock
	private ExamService examService;
	
	
	@BeforeEach
	void setUp () {
		MockitoAnnotations.initMocks ( this );
	}
	
	private Question getNewQuestion () {
		Question question = new Question ();
		question.setId ( 1L );
		question.setQuestion ( "test question" );
		question.setBankId ( "Math" );
		Option option = new Option ();
		option.setAnswer ( "test answer" );
		option.setCorrect ( true );
		question.setOptions ( new HashSet<> ( singletonList ( option ) ) );
		return question;
	}
	
	private Set<Question> getNewQuestions () {
		Question question1 = new Question ();
		question1.setId ( 1L );
		question1.setQuestion ( "math test question" );
		question1.setBankId ( "Math" );
		question1.setOptions ( new HashSet<> (  ) );
		
		Question question2 = new Question ();
		question2.setId ( 2L );
		question2.setQuestion ( "english test question" );
		question2.setBankId ( "Math" );
		question2.setOptions ( new HashSet<> (  ) );
		
		return new HashSet<> (asList ( question1, question2 ));
	}
	
	@Test
	void getQuestions () {
		//Arrange
		Set<Question> questions = getNewQuestions();
		String bankId = "Math";
		
		//Act
		when ( questionService.getQuestions ( bankId ) ).thenReturn ( questions );
		Set< ExamQuestion > examQuestions = questions.stream ().map ( ExamServiceImpl :: convertQuestionToExamQuestion ).collect( Collectors.toSet());
		when ( examService.getQuestions ( bankId ) ).thenReturn ( examQuestions );
		
		//Assert
		assertEquals ( examService.getQuestions ( bankId ) , examQuestions );
		assertEquals ( questionService.getQuestions ( bankId ) , questions );
		assertEquals ( examQuestions.size (), 2 );
		assertEquals ( questions.size (), 2 );
		
		verify ( questionService, times ( 1 ) ).getQuestions ( bankId );
		verify ( examService, times ( 1 ) ).getQuestions ( bankId );
	}
	
	@Test
	void getQuestionInBanks () {
	}
	
	@Test
	void getQuestion () {
		//Arrange
		Question question = getNewQuestion ();
		
		long questionId = question.getId ();
		//Act
		when(questionService.getQuestion ( questionId )).thenReturn ( question );
		ExamQuestion examQuestion = ExamServiceImpl.convertQuestionToExamQuestion ( question );
		when(examService.getQuestion ( questionId )).thenReturn ( examQuestion );
		
		//Assert
		assertEquals ( questionService.getQuestion ( questionId ), question );
		assertEquals ( examService.getQuestion ( questionId ), examQuestion );
		verify ( questionService, times ( 1 ) ).getQuestion ( questionId );
		verify ( examService, times ( 1 ) ).getQuestion ( questionId );
	}
	
	@Test
	void submitAnswer () {
		//Arrange
		Question question = getNewQuestion ();
		ExamAnswerCmd answer = new ExamAnswerCmd ();
		answer.setQuestionId ( 1L );
		answer.setAnswer ( "test answer" );
		
		//Act
		when ( questionService.getQuestion ( answer.getQuestionId () ) ).thenReturn ( question );
		Question expectedQuestion = questionService.getQuestion ( answer.getQuestionId () );
		
		//Assert
		assertEquals ( expectedQuestion, question );
		assertEquals ( expectedQuestion.getOptions ().stream ().findFirst ().get ().getAnswer (), answer.getAnswer (),answer.getAnswer () );
//		verify ( examService, times ( 1 ) ).submitAnswer ( answer );
		verify ( questionService, times ( 1 ) ).getQuestion ( answer.getQuestionId () );
	}
	
	@Test
	void convertQuestionsToExamQuestions () {}
}