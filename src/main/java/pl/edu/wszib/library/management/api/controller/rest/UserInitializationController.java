package pl.edu.wszib.library.management.api.controller.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.library.management.api.model.User;
import pl.edu.wszib.library.management.api.service.impl.UserService;

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
}