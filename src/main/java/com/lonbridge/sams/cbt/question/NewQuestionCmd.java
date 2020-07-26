package com.lonbridge.sams.cbt.question;

import com.lonbridge.sams.cbt.bank.Bank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewQuestionCmd {
 //   private String bankId;
    private String description;
    private String tag;
    private String category;
    private List<Option> options = new ArrayList<>();
    private Bank bank;

}
