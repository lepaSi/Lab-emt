package mk.ukim.finki.emtaud.model.dto;

import lombok.Data;
import mk.ukim.finki.emtaud.model.enumerations.CategoryType;

@Data
public class BookDto {

    private String name;

    private CategoryType category;

    private Long author;

    private Integer availableCopies;

    public BookDto(String name, CategoryType category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
