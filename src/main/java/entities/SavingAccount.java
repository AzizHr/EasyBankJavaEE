package entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SavingAccount extends Account {

    private double interest;

    @Override
    public String toString() {
        return "SavingAccount{" +
                "interest=" + interest +
                '}';
    }
}
