package com.lonbridge.sams.cbt.web;

import com.lonbridge.sams.cbt.NewQuestionCmd;
import com.lonbridge.sams.cbt.Question;
import com.lonbridge.sams.cbt.QuestionService;
import com.lonbridge.sams.cbt.UpdateQuestionCmd;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Api(value = "Question bank", protocols = "https", description = "Manage cbt questions for SAMS")
@RequestMapping("v1/questions/")
@RestController
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Question>> getQuestions(String bankId) {
        Set<Question> questions = questionService.getQuestions(bankId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping
    public ResponseEntity<Question> getQuestion(Long questionId) {
        Question question = questionService.getQuestion(questionId);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteQuestion(Long questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion( NewQuestionCmd cmd) {
        Question question = questionService.addQuestion(cmd);
        return ResponseEntity.ok(question);
    }

    @PutMapping
    public ResponseEntity<Question> addQuestion(@RequestBody UpdateQuestionCmd cmd) {
        Question question = questionService.updateQuestion(cmd);
        return ResponseEntity.ok(question);
    }

}
