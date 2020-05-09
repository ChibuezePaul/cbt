package com.lonbridge.sams.cbt;

import lombok.Data;

@Data
public class ExamAnswerCmd {
	private Long questionId;
	private String answer;
}
