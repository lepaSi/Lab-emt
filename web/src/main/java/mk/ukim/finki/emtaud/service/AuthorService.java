package mk.ukim.finki.emtaud.service;

import mk.ukim.finki.emtaud.model.Author;
import mk.ukim.finki.emtaud.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long Id);
    List<Author> findAll();

    Optional<Author> save(String name, String surname, Long countryId);

    Optional<Author>save(AuthorDto authorDto);

    void delete(Long id);
}
