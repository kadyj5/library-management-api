package pl.edu.wszib.library.management.api.controller.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.library.management.api.model.User;
import pl.edu.wszib.library.management.api.service.impl.UserService;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/users")
public class UserInitializationController {


    @Autowired
    UserService userService;


    public UserInitializationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/init")
    public ResponseEntity<User> initialize(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(path = "/init")
    public ResponseEntity<List<User>> initialize() {
        return ResponseEntity.status(HttpStatus.OK).body((userService.getAll()));
    }

    @PostMapping(path = "/default")
    public ResponseEntity<List<User>> defaultMethod() throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.defaultInitialize());
    }
}