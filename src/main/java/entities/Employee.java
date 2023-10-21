package entities;


import javax.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee extends Person {

    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "email")
    private String email;
//    @Transient
//    private List<Operation> operations;
//    @Transient
//    private List<MissionHistory> missionHistories;

    @Override
    public String toString() {
        return "Employee{" +
                "code='" + code + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
