package pl.edu.wszib.library.management.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "tBook")
public class Book {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @Builder.Default private String title = "N/A";
    @NonNull
    @Builder.Default private String author = "N/A";
    @NonNull
    @Builder.Default private String isbn = "N/A";

    @NonNull
    private boolean available;
    private Date dateOfBorrow;
    private Date expectedDateOfReturn;

    public Book(int id) {
        this.id = id;
    }
}
