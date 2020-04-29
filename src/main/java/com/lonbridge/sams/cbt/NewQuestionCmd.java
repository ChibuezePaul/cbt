package com.lonbridge.sams.cbt;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewQuestionCmd {
    private String bankId;
    private String description;
    private List<Option> options = new ArrayList<>();

}
