package com.lonbridge.sams.cbt.question;

import com.lonbridge.sams.cbt.bank.Bank;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Question {

  //  private String bankId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @ElementCollection
    private Set<Option> options;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Bank bank;

    public Boolean isMultipleEntry(){
        return  options.stream().filter(Option::getCorrect).count() > 1;
    }

}
