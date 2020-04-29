package com.lonbridge.sams.cbt;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Question {

    private String bankId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;

    @ElementCollection
    private Set<Option> options;

    public Boolean isMultipleEntry(){
        return  options.stream().filter(Option::getCorrect).count() > 1;
    }

}
