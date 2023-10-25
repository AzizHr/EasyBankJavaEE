package entities;

import lombok.*;
import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "payment")
public class Payment extends Operation {

    @Transient
    private Account destinationAccount;
    
    @Override
    public String toString() {
        return "Payment{" +
                "destinationAccount=" + destinationAccount +
                '}';
    }
}
