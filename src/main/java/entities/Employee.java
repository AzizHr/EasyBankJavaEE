package entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee extends Person {

    @Column(name = "email")
    private String email;
    @Transient
    private List<Operation> operations;
    @Transient
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
