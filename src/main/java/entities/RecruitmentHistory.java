package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "recruitment_history")
public class RecruitmentHistory {

    @Id
    @ManyToOne
    @Column(name = "employee_code")
    private Employee employee;
    @Id
    @ManyToOne
    @Column(name = "agency_code")
    private Agency agency;
    @Column(name = "recruited_at")
    private LocalDate recruitedAt;
    @Column(name = "finished_at")
    private LocalDate finishedAt;

    @Override
    public String toString() {
        return "IRecruitmentHistoryDAO{" +
                "employee=" + employee +
                ", agency=" + agency +
                ", recruitedAt=" + recruitedAt +
                ", finishedAt=" + finishedAt +
                '}';
    }
}
