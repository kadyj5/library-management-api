package pl.edu.wszib.library.management.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.library.management.api.model.BorrowBookHistory;
import pl.edu.wszib.library.management.api.service.ILibraryService;

@Controller
public class CheckOutHistoryController {


    @Autowired
    ILibraryService libraryService;

    @RequestMapping(path = "/book/add/{id}", method = RequestMethod.GET)
    public String borrowBook(@PathVariable int id) {
        this.libraryService.borrowBook(id);
        BorrowBookHistory borrowBookHistory = new BorrowBookHistory();
        return "redirect:/main";
    }
}
