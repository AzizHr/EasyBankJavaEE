package entities;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Agency {

    private String code;
    private String name;
    private String adress;
    private String phoneNumber;
    private ArrayList<Account> accounts;
    private ArrayList<Employee> employees;

    @Override
    public String toString() {
        return "Agency{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accounts=" + accounts +
                ", employees=" + employees +
                '}';
    }
}
