package pl.edu.wszib.library.management.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "tCheckOut")
public class BorrowBookHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Client client;

    public BorrowBookHistory(int id) {
        this.id = id;
    }
}
