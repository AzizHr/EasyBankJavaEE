package entities;

import enums.demandStatus;
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
    @Transient
    @Column(name = "client_code")
    private Client client;
    @Transient
    @Column(name = "agency_code")
    private Agency agency;

    public Demand(String number, LocalDate createAt, demandStatus status, double price, int duration, double paidMonthly, String remarks, Client client, Agency agency) {
        super(number, createAt, status, price, duration);
        this.paidMonthly = paidMonthly;
        this.remarks = remarks;
        this.client = client;
        this.agency = agency;
    }
}
