package mk.ukim.finki.emtaud.service;

import mk.ukim.finki.emtaud.model.Book;
import mk.ukim.finki.emtaud.model.dto.BookDto;
import mk.ukim.finki.emtaud.model.enumerations.CategoryType;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book>save(String name, CategoryType type, Long authorId, Integer avaliableCopies);
    Optional<Book>save(BookDto bookDto);

    void delete(Long id);
    Optional<Book>edit(Long id, String name, CategoryType type, Long authorId, Integer avaliableCopies);
    Optional<Book>edit(Long id,BookDto bookDto);

    List<Book>findAll();

    Optional<Book> findById(Long id);

    void markasTaken(Long bookId);

    void refreshMaterializedView();

    Page<Book> findAllWithPaggination(Pageable pageable);

}
