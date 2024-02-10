package pl.edu.wszib.library.management.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.library.management.api.model.User;

@Controller
public class LibraryManagementApiController {

    @RequestMapping(path = {"/", "/main", "/index"}, method = RequestMethod.GET)
    public String main() {
        return "index";
    }

    @RequestMapping(path = "/admin/registration", method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute("dataModel", new User());
        return "registration";
    }
    @RequestMapping(path = "/admin/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user) {
        
        return "registration";
    }
}
