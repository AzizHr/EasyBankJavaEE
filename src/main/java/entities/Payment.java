package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
