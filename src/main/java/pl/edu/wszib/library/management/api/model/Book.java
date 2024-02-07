package pl.edu.wszib.library.management.api.model;

import lombok.Data;

@Data
public class Book {

    private final String name;
    private final String author;
    private final String isbn;

    private boolean isRented;
    private String dateOfBorrow;
    private String dateOfReturn;


}
