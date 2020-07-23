package com.lonbridge.sams.cbt.examquestion;

import lombok.Data;

@Data
public class ExamAnswerCmd {
	private Long questionId;
	private String answer;
}
