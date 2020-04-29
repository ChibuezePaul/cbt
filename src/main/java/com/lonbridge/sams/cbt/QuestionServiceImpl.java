package com.lonbridge.sams.cbt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Question> getQuestions(String bankId) {
        log.info("Retrieving questions from {}", bankId);
        return repository.findByBankId(bankId);
    }

    @Override
    public Question getQuestion(long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
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
        question.setQuestion(cmd.getDescription());
        Set<Option> options = new HashSet<>();
        options.addAll(cmd.getOptions());
        question.setOptions(options);
        question.setBankId(cmd.getBankId());
        return repository.save(question);
    }

    @Override
    public Question updateQuestion(UpdateQuestionCmd cmd) {
        Question question = repository.findById(cmd.getId()).orElseThrow(EntityNotFoundException::new);
        question.setQuestion(cmd.getDescription());
        question.setOptions(cmd.getOptions());
        return repository.save(question);
    }


}
