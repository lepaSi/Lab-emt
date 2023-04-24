package mk.ukim.finki.emtaud.repository;

import mk.ukim.finki.emtaud.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    void deleteById(Long id);
    void deleteByName(String name);
    Page<Book> findAll(Pageable pageable);

}
