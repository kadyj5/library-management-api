package pl.edu.wszib.library.management.api.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.library.management.api.dao.impl.IBookDAO;
import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.Client;
import pl.edu.wszib.library.management.api.model.User;
import pl.edu.wszib.library.management.api.service.ILibraryService;
import pl.edu.wszib.library.management.api.session.SessionObject;

import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
public class LibraryManagementApiController {

    @Autowired
    ILibraryService libraryService;
    @Resource
    SessionObject sessionObject;

    @RequestMapping(path = {"/", "/main", "/index"}, method = RequestMethod.GET)
    public String main(Model model) {
        List<Book> books = libraryService.getAll();
        model.addAttribute("books", books);
        model.addAttribute("isLogged", sessionObject.isLogged());
        return "index";
    }

    @RequestMapping(path = "/borrow/{id}", method = RequestMethod.GET)
    public String borrow(@PathVariable int id, Model model) {
        model.addAttribute("clientModel", new Client());
        model.addAttribute("isLogged", sessionObject.isLogged());
        log.info("Borrowing book with id {}. ", id);
        return "userInput";
    }

    @RequestMapping(path = "/borrow/book/{id}", method = RequestMethod.POST)
    public String borrow(@PathVariable int id,
                         @ModelAttribute Client client) {

        return "redirect:/main";
    }

    @RequestMapping(path = "/admin/registration", method = RequestMethod.GET)
    public String registerUser(Model model) {
        model.addAttribute("dataModel", new User());
        return "asd";
    }

    @RequestMapping(path = "/admin/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user) {

        return "as";
    }
}
