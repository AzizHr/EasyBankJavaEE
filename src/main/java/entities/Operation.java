package entities;

import enums.paymentType;
import  lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @Column(name = "number")
    private String number;
    @Column(name = "number")
    private LocalDateTime createdAt;
    @Column(name = "number")
    private double price;
    @Column(name = "number")
    private paymentType payment;
    @Transient
    private Employee employee;
    @Transient
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
