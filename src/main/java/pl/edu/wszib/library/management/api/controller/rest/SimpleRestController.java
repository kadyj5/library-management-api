package pl.edu.wszib.library.management.api.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.library.management.api.model.Book;
import pl.edu.wszib.library.management.api.model.User;

import java.util.Optional;

@RestController
public class SimpleRestController {


    @PostMapping(path = "book/")
    public void initialize(@RequestBody Book book) {
        System.out.println(book);
    }

//    @GetMapping(path = "book/{id}")
//    public ResponseEntity<Book> getBook(@PathVariable int id){
//        Optional<Book> book = this.bookDAO.getById(id);
//
//        ResponseEntity.ok(book.get());
//    }
    @PostMapping(path = "user/")
    public void initialize(@RequestBody User user) {
        System.out.println(user);
    }


}
