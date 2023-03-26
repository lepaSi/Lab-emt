package mk.ukim.finki.emtaud.model.dto;

import lombok.Data;

@Data
public class AuthorDto {
    private String name;

    private String surname;

    public AuthorDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
