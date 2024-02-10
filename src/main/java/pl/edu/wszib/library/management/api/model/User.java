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
@Entity(name = "tUser")
public class User {

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;

}
