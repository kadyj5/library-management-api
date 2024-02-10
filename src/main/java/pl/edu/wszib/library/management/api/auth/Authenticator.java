package pl.edu.wszib.library.management.api.auth;

import org.springframework.stereotype.Component;
import pl.edu.wszib.library.management.api.model.User;

@Component
public class Authenticator implements IAuthenticator{

    @Override
    public void authenticate(User user) {

    }

}
