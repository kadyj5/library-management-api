package pl.edu.wszib.library.management.api.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.Client;
import pl.edu.wszib.library.management.api.model.User;
import pl.edu.wszib.library.management.api.service.ILibraryService;
import pl.edu.wszib.library.management.api.session.SessionObject;

import java.util.List;

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

    @RequestMapping(path = "/borrow/{id}", method = RequestMethod.POST)
    public String borrow(@PathVariable int id,
                         @ModelAttribute Client client) {

        log.info("Borrowing book with id {}. ", id);
        libraryService.borrowBook(id, client);
        return "redirect:/main";
    }

    @RequestMapping(path = "/return/{id}", method = RequestMethod.GET)
    public String returnBook(@PathVariable int id) {

        log.info("Returning book with id {}. ", id);
        libraryService.returnBook(id);
        return "redirect:/main";
    }

    @RequestMapping(path = "/book/add", method = RequestMethod.GET)
    public String returnBook(Model model) {
        model.addAttribute("bookModel", new Book());
        model.addAttribute("isLogged", sessionObject.isLogged());

        log.info("Adding book.");
        return "bookAdd";
    }

    @RequestMapping(path = "/book/add", method = RequestMethod.POST)
    public String returnBook(@ModelAttribute Book book) {
        libraryService.addBook(book);

        return "redirect:/main";
    }
}
