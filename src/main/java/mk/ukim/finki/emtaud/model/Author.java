package mk.ukim.finki.emtaud.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name")
    private String name;

    @Column(name = "author_surname")
    private String surname;

    @ManyToOne
    private Country country;

    public Author(){}

    public Author(String name, String surname){
        this.name=name;
        this.surname=surname;
    }

    public Author(String name, String surname, Country country){
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
