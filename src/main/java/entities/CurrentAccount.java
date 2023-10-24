package entities;

import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "current_account")
public class CurrentAccount extends Account {

    @Column(name = "overdraft")
    private double overdraft;

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "overdraft=" + overdraft +
                '}';
    }
}
