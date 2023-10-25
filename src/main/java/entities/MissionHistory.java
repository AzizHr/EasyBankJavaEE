package entities;


import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "mission_history")
public class MissionHistory {

    @Id
    @ManyToOne
    @Column(name = "employee_code")
    private Employee employee;
    @Id
    @ManyToOne
    @Column(name = "mission_code")
    private  Mission mission;
    @Id
    @Column(name = "started_at")
    private LocalDate startedAt;
    @Column(name = "ended_at")
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
