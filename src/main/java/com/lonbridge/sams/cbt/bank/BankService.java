package com.lonbridge.sams.cbt.bank;

import com.lonbridge.sams.cbt.question.Question;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface BankService {

    Bank getBank(Long id);

    Bank addBank(NewBankCmd cmd);

    Bank updateBank(UpdateBankCmd cmd);

    Set<Bank> getMultipleBanks(List<UpdateBankCmd> cmd);

    void deleteBank(long id);

/*    Question addQuestion(NewQuestionCmd cmd);

    Question updateQuestion(UpdateQuestionCmd cmd);

    List< Question > addQuestions ( List< NewQuestionCmd> cmds );*/


}
