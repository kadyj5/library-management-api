package pl.edu.wszib.library.management.api.auth;

import pl.edu.wszib.library.management.api.model.User;

public interface IAuthenticator {
    void authenticate(User user);
}
