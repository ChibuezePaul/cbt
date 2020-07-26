package com.lonbridge.sams.cbt.web;

import com.lonbridge.sams.cbt.bank.Bank;
import com.lonbridge.sams.cbt.bank.BankService;
import com.lonbridge.sams.cbt.bank.NewBankCmd;
import com.lonbridge.sams.cbt.bank.UpdateBankCmd;
import com.lonbridge.sams.cbt.question.NewQuestionCmd;
import com.lonbridge.sams.cbt.question.Question;
import com.lonbridge.sams.cbt.question.QuestionService;
import com.lonbridge.sams.cbt.question.UpdateQuestionCmd;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Api(value = "Question bank", protocols = "https", description = "Manage cbt questions for SAMS")
@RequestMapping("v1/bank")
@RestController
public class BankController {

    private BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

/*    @GetMapping("/all")
    public ResponseEntity<Set<Question>> getQuestions(String bankId) {
        Set<Question> questions = bankService.getQuestions(bankId);
        return ResponseEntity.ok(questions);
    }*/
    
    @GetMapping("/banks")
    public ResponseEntity<List<?>> getMultipleBanks(@RequestBody UpdateBankCmd... cmd) {
  //  public ResponseEntity<Set<?>> getMultipleBanks(@RequestBody Long... bankId) {
        Set<Bank> banks = bankService.getMultipleBanks(cmd);
        return ResponseEntity.ok(Arrays.asList(banks));
    }

    @GetMapping
    public ResponseEntity<Bank> getBank(Long id) {
        Bank bank = bankService.getBank(id);
        return ResponseEntity.ok(bank);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBank(Long id) {
        bankService.deleteBank(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> addBank(@RequestBody NewBankCmd cmd) {
        Bank bank = bankService.addBank(cmd);
        return ResponseEntity.ok(bank);
    }
    
/*    @PostMapping("/all")
    public ResponseEntity<List<Question>> addQuestions( @RequestBody NewQuestionCmd... cmds ) {
        List< Question > question = questionService.addQuestions( Arrays.asList ( cmds ) );
        return ResponseEntity.ok(question);
    }*/

    @PutMapping
    public ResponseEntity<?> updateBank (@RequestBody UpdateBankCmd cmd) {
        Bank bank = bankService.updateBank(cmd);
        return ResponseEntity.ok(bank);
    }

}
