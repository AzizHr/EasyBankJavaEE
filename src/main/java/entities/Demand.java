package entities;

import lombok.*;
import jakarta.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "demand")
public class Demand extends Simulation {

    @Column(name = "paid_monthly")
    private double paidMonthly;
    @Column(name = "remarks")
    private String remarks;
    @Transient
    private Client client;
    @Transient
    private Agency agency;

}
