package entities;

import java.util.List;
import lombok.*;
import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "client")
public class Client extends Person {

    @Column(name = "address")
    private String address;
    @Transient
    private Employee employee;
    @Transient
    private List<Account> accounts;

    @Override
    public String toString() {
        return "Client{" +
                "adress='" + address + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
