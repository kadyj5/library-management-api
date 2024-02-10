package pl.edu.wszib.library.management.api.service.impl;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.library.management.api.dao.impl.IUserDAO;
import pl.edu.wszib.library.management.api.model.User;
import pl.edu.wszib.library.management.api.service.IAuthenticationService;
import pl.edu.wszib.library.management.api.session.SessionObject;

import java.util.Optional;

@Component
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;
    @Resource
    SessionObject sessionObject;

    @Override
    public void login(String login, String password) {
        Optional<User> userBox = this.userDAO.getUserByLogin(login);
        if (userBox.isPresent() &&
                userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setLoggedUser(userBox.get());
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }
}
