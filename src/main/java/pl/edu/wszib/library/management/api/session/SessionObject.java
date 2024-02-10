package pl.edu.wszib.library.management.api.session;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.library.management.api.model.User;

import java.util.Objects;

@Getter
@Setter
@Component
@SessionScope
public class SessionObject {
    private User loggedUser;

    public boolean isLogged() {
        return Objects.nonNull(loggedUser);
    }
}
