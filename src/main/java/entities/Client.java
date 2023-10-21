package entities;

import java.util.List;
import lombok.*;
import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "client")
public class Client extends Person {

    @Id
    @Column(name = "code")
    private String code;
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
