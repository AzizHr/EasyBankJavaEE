package entities;

import enums.demandStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@MappedSuperclass
public class Simulation {

    @Id
    @Column(name = "number")
    private String number;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private demandStatus status;
    @Column(name = "price")
    private double price;
    @Column(name = "duration")
    private int duration;

}
