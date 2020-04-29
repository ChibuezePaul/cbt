package com.lonbridge.sams.cbt;

import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Objects;

@Data
@Embeddable
public class Option {
    private String answer;
    private Boolean correct = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return Objects.equals(answer, option.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }
}
