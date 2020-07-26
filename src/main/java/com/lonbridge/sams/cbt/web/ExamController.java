package com.lonbridge.sams.cbt.web;

import com.lonbridge.sams.cbt.examquestion.ExamAnswerCmd;
import com.lonbridge.sams.cbt.examquestion.ExamQuestion;
import com.lonbridge.sams.cbt.examquestion.ExamService;
import com.lonbridge.sams.cbt.question.Question;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api (value = "Exam Question", protocols = "https", description = "Manage cbt exam questions for SAMS")
@RequestMapping ("v1/examquestion")
@RestController
public class ExamController {
	
	private final ExamService examService;
	
	@Autowired
	public ExamController ( ExamService examService ) {this.examService = examService;}
	
	@GetMapping ("/all")
	public ResponseEntity< Set<ExamQuestion> > getExamQuestions(Long bankId) {
		Set<ExamQuestion> questions = examService.getQuestions(bankId);
		return ResponseEntity.ok(questions);
	}
	
	@GetMapping ("/banks")
	public ResponseEntity< Set<ExamQuestion> > getExamQuestionsFromMultipleBanks(String... bankId) {
		Set<ExamQuestion> questions = examService.getQuestionInBanks (bankId);
		return ResponseEntity.ok(questions);
	}
	
	@GetMapping
	public ResponseEntity<ExamQuestion> getExamQuestion(Long questionId) {
		ExamQuestion question = examService.getQuestion(questionId);
		return ResponseEntity.ok (question);
	}
	
	@PostMapping
	public ResponseEntity submitAnswer(ExamAnswerCmd answerCmd){
		examService.submitAnswer ( answerCmd );
		return ResponseEntity.ok ().build ();
	}
	
	@PutMapping ("/convert")
	public ResponseEntity< Set<ExamQuestion> > convertQuestionsToExamQuestions(@RequestBody Set< Question > questions) {
		Set<ExamQuestion> examQuestions = examService.convertQuestionsToExamQuestions ( questions );
		return ResponseEntity.ok(examQuestions);
	}
	
}
