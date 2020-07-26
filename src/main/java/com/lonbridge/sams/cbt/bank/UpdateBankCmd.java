package com.lonbridge.sams.cbt.bank;

import com.lonbridge.sams.cbt.question.Option;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateBankCmd {

    private long id;
    private String description;

}
