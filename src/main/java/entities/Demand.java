package entities;

import enums.DemandStatus;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "demand")
public class Demand extends Simulation {

    @Column(name = "paid_monthly")
    private double paidMonthly;
    @Column(name = "remarks")
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "client_code", referencedColumnName = "code")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "agency_code", referencedColumnName = "code")
    private Agency agency;

    public Demand(String number, LocalDate createAt, DemandStatus status, double price, int duration, double paidMonthly, String remarks, Client client, Agency agency) {
        super(number, createAt, status, price, duration);
        this.paidMonthly = paidMonthly;
        this.remarks = remarks;
        this.client = client;
        this.agency = agency;
    }
}
