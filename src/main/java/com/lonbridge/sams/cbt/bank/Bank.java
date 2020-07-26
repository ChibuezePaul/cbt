package com.lonbridge.sams.cbt.bank;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    public Bank(String description){
        this.description = description;
    }

    public Bank(){

    }

/*    @Override
    public boolean equals(Object b) {
        if (this == b) return true;
        if (b == null || getClass() != b.getClass()) return false;
        Bank option = (Bank) b;
        return Objects.equals(description, option.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }*/
}
