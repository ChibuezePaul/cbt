package com.lonbridge.sams.cbt.bank;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface BankService {

    Bank getBank(String id);

    Bank addBank(NewBankCmd cmd);

    Bank updateBank(UpdateBankCmd cmd);

    Set<Bank> getMultipleBanks(UpdateBankCmd... cmd);

    void deleteBank(String id);
	
	List< Bank> getAllBank ();

/*    Question addQuestion(NewQuestionCmd cmd);

    Question updateQuestion(UpdateQuestionCmd cmd);

    List< Question > addQuestions ( List< NewQuestionCmd> cmds );*/


}
