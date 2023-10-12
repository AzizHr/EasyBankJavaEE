package entities;

import lombok.*;

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
