package com.lonbridge.sams.cbt.assessment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AssessmentSummary {
    List<Assessment> assessments;
    Long totalPoint;
}
