package com.lonbridge.sams.cbt.examquestion;

import lombok.Data;
import java.util.Set;

@Data
public class ExamQuestion {
    private String description;
    private Boolean isMultipleEntry;
    private Set<ExamAnswerCmd> answers;
}
