package com.lonbridge.sams.cbt.question;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateQuestionCmd {

    private long id;
    private String description;
    private Set<Option> options;

}
