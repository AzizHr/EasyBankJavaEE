package entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "agency")
public class Agency {

    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Transient
    private ArrayList<Account> accounts;
    @Transient
    private ArrayList<Employee> employees;

    @Override
    public String toString() {
        return "Agency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", adress='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accounts=" + accounts +
                ", employees=" + employees +
                '}';
    }
}
