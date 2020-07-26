package com.lonbridge.sams.cbt.question;

import com.lonbridge.sams.cbt.bank.Bank;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateQuestionCmd {

    private long id;
    private String description;
    private String tag;
    private String category;
    private Set<Option> options;
    //private Bank bank;

}
