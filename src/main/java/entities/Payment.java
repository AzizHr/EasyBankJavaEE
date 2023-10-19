package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

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
