package pl.edu.wszib.library.management.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.wszib.library.management.api.common.UserAccessLevel;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private UserAccessLevel userAccessLevel;
    private String name;
    private String surname;
    private int age;
    private String login;
    private String password;

    public boolean isAdmin() {
        return this.userAccessLevel.equals(UserAccessLevel.ADMIN);
    }
    public boolean isRegistered() {
        return this.userAccessLevel.equals(UserAccessLevel.REGISTERED);
    }
}
