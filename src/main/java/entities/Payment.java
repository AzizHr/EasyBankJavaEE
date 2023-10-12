package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Payment extends Operation {

    private Account destinationAccount;
    
    @Override
    public String toString() {
        return "Payment{" +
                "destinationAccount=" + destinationAccount +
                '}';
    }
}
