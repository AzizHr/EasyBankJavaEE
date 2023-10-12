package entities;

import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public abstract class Person {

    private String code;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;

}
