package entities;

import enums.DemandStatus;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
//@AllArgsConstructor
@Setter
@Getter
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Simulation {

    @Id
    @Column(name = "number")
    private int number;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DemandStatus status;
    @Column(name = "price")
    private double price;
    @Column(name = "duration")
    private int duration;

    public Simulation(LocalDate createAt, DemandStatus status, double price, int duration) {
        this.createAt = createAt;
        this.status = status;
        this.price = price;
        this.duration = duration;
    }
}
