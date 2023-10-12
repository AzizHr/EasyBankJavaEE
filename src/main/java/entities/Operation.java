package entities;

import enums.paymentType;
import  lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Operation {

    private String number;
    // Updated
    private LocalDateTime createdAt;
    private double price;
    private paymentType payment;
    private Employee employee;
    private Account account;

    @Override
    public String toString() {
        return "Operation{" +
                "number='" + number + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", payment=" + payment +
                ", employee=" + employee +
                ", account=" + account +
                '}';
    }
}
