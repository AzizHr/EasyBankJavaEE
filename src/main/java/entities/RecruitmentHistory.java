package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RecruitmentHistory {

    private Employee employee;
    private Agency agency;
    private LocalDate recruitedAt;
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
