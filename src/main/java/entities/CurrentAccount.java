package entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CurrentAccount extends Account {

    private double overdraft;

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "overdraft=" + overdraft +
                '}';
    }
}
