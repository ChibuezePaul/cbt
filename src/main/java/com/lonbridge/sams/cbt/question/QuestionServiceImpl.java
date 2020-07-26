package com.lonbridge.sams.cbt.question;

import com.lonbridge.sams.cbt.bank.Bank;
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

    private QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Question> getQuestions(Long bankId) {
        log.info("Retrieving questions from {}", bankId);
        return repository.findByBankId(bankId);
    }
    
    @Override
    public Set< Question > getQuestionInBanks ( String... bankId ) {
        log.info("Retrieving questions from {}", Arrays.toString ( bankId ) );
        return repository.findByBankIdIn ( bankId );
    }
    
    @Override
    public Question getQuestion(long id) {
        return repository.findById(id).orElseThrow( QuestionNotFoundException ::new);
    }

    @Override
    public void deleteQuestion(long id) {
        log.info("Deleting {}", id);
        repository.deleteById(id);
    }

    @Override
    public Question addQuestion(NewQuestionCmd cmd) {
        log.info("Saving {}",cmd);
        Question question = new Question();
        question.setDescription (cmd.getDescription());
        Set< Option > options = new HashSet<> ( cmd.getOptions () );
     //   Bank bank = new Bank(cmd.getBank().getDescription());
        question.setOptions(options);
        question.setBank(cmd.getBank());
        return repository.save(question);
    }

    @Override
    public Question updateQuestion(UpdateQuestionCmd cmd) {
        Question question = repository.findById(cmd.getId()).orElseThrow(QuestionNotFoundException::new);
        question.setDescription (cmd.getDescription());
        question.setOptions(cmd.getOptions());
        return repository.save(question);
    }
    
    @Override
    public List< Question > addQuestions ( List< NewQuestionCmd > cmds ) {
        return cmds.stream ()
              .map ( this :: addQuestion )
              .collect ( Collectors.toList () );
    }
}
