package entities;


import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MissionHistory {

    private Employee employee;
    private  Mission mission;
    private LocalDate startedAt;
    private LocalDate endedAt;

    @Override
    public String toString() {
        return "MissionHistory{" +
                "employee=" + employee +
                ", mission=" + mission +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                '}';
    }
}
