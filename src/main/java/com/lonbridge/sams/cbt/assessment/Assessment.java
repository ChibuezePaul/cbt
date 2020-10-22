package com.lonbridge.sams.cbt.assessment;

import lombok.Data;

@Data
public class Assessment {
    private Long questionId;
    private String question;
    private String answer;
    private Boolean correct;
    private Long questionPoint;
}
