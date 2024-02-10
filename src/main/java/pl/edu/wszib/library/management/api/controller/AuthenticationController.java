package pl.edu.wszib.library.management.api.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wszib.library.management.api.model.User;
import pl.edu.wszib.library.management.api.service.IAuthenticationService;
import pl.edu.wszib.library.management.api.session.SessionObject;

@Controller
@Slf4j
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;
    @Resource
    SessionObject sessionObject;

    @GetMapping(path = "/login")
    public String login(Model model) {
        model.addAttribute("userModel", new User());
        model.addAttribute("isLogged", sessionObject.isLogged());
        log.info("Authentication for user in progress...");

        return "login";
    }

    @PostMapping(path = "/login")
    public String login(@ModelAttribute User user) {
        this.authenticationService.login(user.getLogin(), user.getPassword());
        if (this.sessionObject.isLogged()) {
            log.info("User authenticated with login: {}", user.getLogin());

            return "redirect:/main";
        }

        return "redirect:/login";
    }

    @GetMapping(path = "/logout")
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/main";
    }

}
