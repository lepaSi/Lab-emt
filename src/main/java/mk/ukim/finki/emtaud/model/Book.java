package mk.ukim.finki.emtaud.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emtaud.model.enumerations.CategoryType;


@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    public Book(){}

    public Book(String name, CategoryType category, Author author, Integer availableCopies){
         this.name = name;
         this.category = category;
         this.author = author;
         this.availableCopies = availableCopies;

    }
}
