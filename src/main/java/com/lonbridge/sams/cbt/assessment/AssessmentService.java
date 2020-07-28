package com.lonbridge.sams.cbt.assessment;

import com.lonbridge.sams.cbt.examquestion.ExamAnswerCmd;
import java.util.List;

public interface AssessmentService {
    AssessmentSummary submitAnswer(List< ExamAnswerCmd > examAnswers);
}
