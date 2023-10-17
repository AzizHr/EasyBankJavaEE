package entities;

import java.util.List;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Client extends Person {

    private String address;
    private List<Account> accounts;

    @Override
    public String toString() {
        return "Client{" +
                "adress='" + address + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
