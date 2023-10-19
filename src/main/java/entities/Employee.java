package entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee extends Person {

    private String email;
    private List<Operation> operations;
    private List<MissionHistory> missionHistories;

    @Override
    public String toString() {
        return "Employee{" +
                "email='" + email + '\'' +
                ", operations=" + operations +
                ", missionHistories=" + missionHistories +
                '}';
    }
}
