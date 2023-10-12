package entities;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
