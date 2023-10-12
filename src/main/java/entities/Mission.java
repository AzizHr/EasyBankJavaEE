package entities;

import java.util.List;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Mission {

    private String code;
    private String name;
    private String description;
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
