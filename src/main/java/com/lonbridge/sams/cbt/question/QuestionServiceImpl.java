package com.lonbridge.sams.cbt.question;

import com.lonbridge.sams.cbt.bank.Bank;
import com.lonbridge.sams.cbt.bank.BankRepository;
import com.lonbridge.sams.cbt.core.BankNotFoundException;
import com.lonbridge.sams.cbt.core.QuestionNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;
    private BankRepository bankRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, BankRepository bankRepository) {
        this.questionRepository = questionRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public Set<Question> getQuestions(String bankId) {
        log.info("Retrieving questions from- {}", bankId);
        return questionRepository.findByBankId(bankId);
    }
    
    @Override
    public Set< Question > getQuestionInBanks ( String... bankId ) {
        log.info("Retrieving questions from {}", Arrays.toString ( bankId ) );
        return questionRepository.findByBankIdIn ( bankId );
    }
    
    @Override
    public Question getQuestion(long id) {
        return questionRepository.findById(id).orElseThrow( QuestionNotFoundException ::new);
    }

    @Override
    public void deleteQuestion(long id) {
        log.info("Deleting {}", id);
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);;
        questionRepository.delete(question);
    }

    @Override
    public Question addQuestion(NewQuestionCmd cmd) {
        log.info("Saving {}",cmd);
        Question question = new Question();
        question.setDescription (cmd.getDescription());
        question.setTag (cmd.getTag());
        question.setCategory (cmd.getCategory());
        Set< Option > options = new HashSet<> ( cmd.getOptions () );
        Bank bank = bankRepository.findById(cmd.getBank().getId()).orElseThrow(BankNotFoundException::new);
        question.setOptions(options);
        question.setBank(bank);
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(UpdateQuestionCmd cmd) {
        Question question = questionRepository.findById(cmd.getId()).orElseThrow(QuestionNotFoundException::new);
        question.setDescription (cmd.getDescription());

        if(cmd.getTag() != null && !cmd.getTag().equalsIgnoreCase("")){
            question.setTag (cmd.getTag());
        }
        if(cmd.getCategory() != null && !cmd.getCategory().equalsIgnoreCase("")){
            question.setCategory (cmd.getCategory());
        }
     //   Bank bank = bankRepository.findById(question.getBank().getId()).orElseThrow(BankNotFoundException::new);
      //  Bank bank = question.getBank();
      //  bank.setId(cmd.getId());

      //  question.setBank(bank);

        question.setOptions(cmd.getOptions());
        return questionRepository.save(question);
    }
    
    @Override
    public List< Question > addQuestions ( List< NewQuestionCmd > cmds ) {
        return cmds.stream ()
              .map ( this :: addQuestion )
              .collect ( Collectors.toList () );
    }
}
