package com.lonbridge.sams.cbt.assessment;

import lombok.Data;

@Data
public class Assessment {
	private Long questionId;
	private String question;
	private Boolean correct;
	private Long questionPoint;
}
