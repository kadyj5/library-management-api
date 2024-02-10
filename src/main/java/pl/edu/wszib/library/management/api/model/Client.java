package pl.edu.wszib.library.management.api.model;

import jakarta.persistence.*;
import lombok.*;
import pl.edu.wszib.library.management.api.common.UserAccessLevel;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "tClient")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private int age;
    private String phone;
}
