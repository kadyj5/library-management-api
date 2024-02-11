package pl.edu.wszib.library.management.api.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.BorrowBookHistory;
import pl.edu.wszib.library.management.api.model.Client;
import pl.edu.wszib.library.management.api.service.IBookService;
import pl.edu.wszib.library.management.api.session.SessionObject;

import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
public class LibraryManagementApiController {

    @Autowired
    IBookService libraryService;
    @Resource
    SessionObject sessionObject;

    @GetMapping(path = {"/main"})
    public String listBooks(Model model,
                            @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {

        List<Book> books;
        if (Objects.nonNull(searchPhrase)) {
            books = libraryService.searchByPhrase(searchPhrase);
        } else {
            books = libraryService.getAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("isLogged", sessionObject.isLogged());

        return "index";
    }

    @GetMapping(path = {"/main/rented"})
    public String listRentedBooks(Model model,
                                  @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {

        List<BorrowBookHistory> borrowBookHistories = libraryService.getRentedBooks(searchPhrase);

        model.addAttribute("borrowBookHistories", borrowBookHistories);
        model.addAttribute("isLogged", sessionObject.isLogged());

        return "notReturnedBooks";
    }

    @GetMapping(path = {"/main/rented/delayed"})
    public String listDelayedBooks(Model model,
                                   @RequestParam(value = "searchPhrase", required = false) String searchPhrase) {

        List<BorrowBookHistory> borrowBookHistories = libraryService.getRentedBooksAfterDate(searchPhrase);
        model.addAttribute("borrowBookHistories", borrowBookHistories);
        model.addAttribute("isLogged", sessionObject.isLogged());

        return "notReturnedBooks";
    }

    @GetMapping(path = "/borrow/{id}")
    public String borrow(@PathVariable int id, Model model) {
        model.addAttribute("clientModel", new Client());
        model.addAttribute("isLogged", sessionObject.isLogged());

        log.info("Borrowing book with id {}. ", id);
        return "userInput";
    }

    @PostMapping(path = "/borrow/{id}")
    public String borrow(@PathVariable int id,
                         @ModelAttribute Client client) {

        log.info("Borrowing book with id {}. ", id);
        libraryService.borrowBook(id, client);
        return "redirect:/main";
    }

    @GetMapping(path = "/return/{id}")
    public String returnBook(@PathVariable int id) {

        log.info("Returning book with id {}. ", id);
        libraryService.returnBook(id);
        return "redirect:/main";
    }

    @GetMapping(path = "/book/add")
    public String returnBook(Model model) {
        model.addAttribute("bookModel", new Book());
        model.addAttribute("isLogged", sessionObject.isLogged());

        log.info("Adding book.");
        return "bookAdd";
    }

    @PostMapping(path = "/book/add")
    public String returnBook(@ModelAttribute Book book) {
        libraryService.addBook(book);

        return "redirect:/main";
    }
}
