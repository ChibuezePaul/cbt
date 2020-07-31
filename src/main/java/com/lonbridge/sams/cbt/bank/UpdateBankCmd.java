package com.lonbridge.sams.cbt.bank;

import com.lonbridge.sams.cbt.question.Option;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateBankCmd {

    private String id;
    private String description;

}
