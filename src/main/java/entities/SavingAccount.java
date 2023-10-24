package entities;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "saving_account")
public class SavingAccount extends Account {

    @Column(name = "interest")
    private double interest;

    @Override
    public String toString() {
        return "SavingAccount{" +
                "interest=" + interest +
                '}';
    }
}
