package entities;

import java.util.List;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "mission")
public class Mission {

    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Transient
    private List<MissionHistory> missionHistories;

    @Override
    public String toString() {
        return "Mission{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", missionHistories=" + missionHistories +
                '}';
    }
}
