package entities;

import java.time.LocalDate;
import lombok.*;
import jakarta.persistence.*;
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "client")
@Inheritance
public class Client extends Person {

    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "employee_code", referencedColumnName = "code")
    private Employee employee;
//    @Transient
//    private List<Account> accounts;

    public Client(String firstName, String lastName, LocalDate birthDate, String phoneNumber, String code, String address, Employee employee) {
        super(firstName, lastName, birthDate, phoneNumber);
        this.code = code;
        this.address = address;
//        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Client{" +
                "adress='" + address + '\'' +
                '}';
    }
}
