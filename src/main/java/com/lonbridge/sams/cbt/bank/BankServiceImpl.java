package com.lonbridge.sams.cbt.bank;


import com.lonbridge.sams.cbt.core.BankNotFoundException;
import com.lonbridge.sams.cbt.core.QuestionNotFoundException;
import com.lonbridge.sams.cbt.question.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BankServiceImpl implements BankService {

   // @Autowired
    private BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank getBank(Long id) {
        return bankRepository.findById(id).orElseThrow( BankNotFoundException::new);
    }

    @Override
    public Bank addBank(NewBankCmd cmd) {
        Bank bank = new Bank();
        bank.setDescription(cmd.getDescription());
        return bankRepository.save(bank);
    }

    @Override
    public Set<Bank> getMultipleBanks (List<UpdateBankCmd> cmd ) {
        log.info("Retrieving multiple banks from {}", Arrays.toString ( cmd.toArray() ) );

        cmd.stream().map(c -> c.getId())
                .collect ( Collectors.toList () );

        return bankRepository.findByIdIn();
      //  return bankRepository.findByBankIdIn ( bankId );
    }

    @Override
    public Bank updateBank(UpdateBankCmd cmd) {
        Bank bank = bankRepository.findById(cmd.getId()).orElseThrow( BankNotFoundException::new);
        return bankRepository.save(bank);
    }

    @Override
    public void deleteBank(long id) {
        bankRepository.deleteById(id);
    }
}
