package com.lonbridge.sams.cbt.web;

import com.lonbridge.sams.cbt.assessment.AssessmentService;
import com.lonbridge.sams.cbt.assessment.AssessmentSummary;
import com.lonbridge.sams.cbt.examquestion.ExamAnswerCmd;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

@Api (value = "Assessment", protocols = "https", description = "Manage cbt exam questions for SAMS")
@RequestMapping ("v1/assessment")
@RestController
public class AssessmentController {

    private AssessmentService assessmentService;

    @Autowired
    public AssessmentController ( AssessmentService assessmentService ) {this.assessmentService = assessmentService;}

    @PostMapping
    public ResponseEntity< AssessmentSummary > submitAnswer( @RequestBody ExamAnswerCmd... examAnswers){
        AssessmentSummary assessments = assessmentService.submitAnswer ( Arrays.asList ( examAnswers ) );
        return ResponseEntity.ok (assessments);
    }
}
