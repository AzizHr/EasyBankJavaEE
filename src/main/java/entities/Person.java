package entities;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@MappedSuperclass
public abstract class Person {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "phone_number")
    private String phoneNumber;

}
