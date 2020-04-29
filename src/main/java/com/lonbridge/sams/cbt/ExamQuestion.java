package com.lonbridge.sams.cbt;

import lombok.Data;

import java.util.Set;

@Data
public class ExamQuestion {
    private String question;

    private Set<String> answers;
}
