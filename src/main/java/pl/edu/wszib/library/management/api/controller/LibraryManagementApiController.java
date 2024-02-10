package pl.edu.wszib.library.management.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.library.management.api.dao.impl.IBookDAO;
import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.User;

import java.util.List;

@Controller
public class LibraryManagementApiController {

    final IBookDAO bookDAO;

    public LibraryManagementApiController(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @RequestMapping(path = {"/", "/main", "/index"}, method = RequestMethod.GET)
    public String main(Model model) {
        List<Book> books = bookDAO.getAll();
        model.addAttribute("books", books);
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
