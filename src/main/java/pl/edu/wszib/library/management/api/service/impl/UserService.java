package pl.edu.wszib.library.management.api.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import pl.edu.wszib.library.management.api.dao.impl.hibernate.UserDAO;
import pl.edu.wszib.library.management.api.model.User;
import pl.edu.wszib.library.management.api.service.IUserService;

import java.util.List;

@Component
public class UserService implements IUserService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<User> getAll() {
        return this.userDAO.getAll();
    }

    @Override
    public void addUser(User user) {

        userDAO.persist(User.builder().login(user.getLogin())
                .password(DigestUtils.md5Hex(user.getPassword())).build());
    }

    @Override
    public List<User> defaultInitialize() throws Exception {
        ClassPathResource resource = new ClassPathResource("jsons/users.json");
        List<User> users = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<User>>() {
        });
        users.forEach(this::addUser);
        return users;
    }
}
