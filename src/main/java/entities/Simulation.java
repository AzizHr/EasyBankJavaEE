package entities;

import enums.demandStatus;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Simulation {

    private String number;
    private LocalDate date;
    private demandStatus status;
    private double price;
    private int duration;

}
