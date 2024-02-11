package pl.edu.wszib.library.management.api.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.User;
import pl.edu.wszib.library.management.api.service.impl.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookInitializationController {

    @Autowired
    BookService bookService;

    public BookInitializationController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path = "/init")
    public ResponseEntity<Book> initialize(@RequestBody Book book) {
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping(path = "/init")
    public ResponseEntity<List<Book>> initialize() {
        return ResponseEntity.status(HttpStatus.OK).body((bookService.getAll()));
    }
    @PostMapping(path = "/default")
    public ResponseEntity<List<Book>> defaultMethod() throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.defaultInitialize());
    }
}
