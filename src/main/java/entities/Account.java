package entities;

import enums.accountStatus;
import java.time.LocalDate;
import java.util.List;
import lombok.*;
import jakarta.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@MappedSuperclass
public abstract class Account {

    @Id
    @Column(name = "number")
    private String number;
    @Column(name = "balance")
    private double balance;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "account_status")
    private accountStatus accountStatus;
    @Transient
    private Agency agency;
    @Transient
    private Client client;
    @Transient
    private Employee employee;
    @Transient
    private List<Operation> operations;

}
