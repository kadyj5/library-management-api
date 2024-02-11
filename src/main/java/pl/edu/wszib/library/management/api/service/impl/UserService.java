package pl.edu.wszib.library.management.api.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.library.management.api.dao.impl.hibernate.UserDAO;
import pl.edu.wszib.library.management.api.model.User;
import pl.edu.wszib.library.management.api.service.IUserService;

import java.util.List;

@Component
public class UserService implements IUserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> getAll() {
        return this.userDAO.getAll();
    }

    @Override
    public void addUser(User user) {

        userDAO.persist(User.builder().login(user.getLogin())
                .password(DigestUtils.md5Hex(user.getPassword())).build());
    }
}
