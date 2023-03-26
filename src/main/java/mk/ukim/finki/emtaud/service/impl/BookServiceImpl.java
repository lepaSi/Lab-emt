package mk.ukim.finki.emtaud.service.impl;

import mk.ukim.finki.emtaud.model.Author;
import mk.ukim.finki.emtaud.model.Book;
import mk.ukim.finki.emtaud.model.dto.BookDto;
import mk.ukim.finki.emtaud.model.enumerations.CategoryType;
import mk.ukim.finki.emtaud.model.events.BookCreatedEvent;
import mk.ukim.finki.emtaud.model.exceptions.BookNoLongerAvailableException;
import mk.ukim.finki.emtaud.model.exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emtaud.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.emtaud.repository.AuthorRepository;
import mk.ukim.finki.emtaud.repository.BookRepository;
import mk.ukim.finki.emtaud.repository.views.BooksPerAuthorViewRepository;
import mk.ukim.finki.emtaud.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BooksPerAuthorViewRepository booksPerAuthorViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Optional<Book> save(String name, CategoryType type, Long authorId, Integer avaliableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new InvalidAuthorIdException(authorId));

        this.bookRepository.deleteByName(name);
        Book book = new Book(name,type,author,avaliableCopies);
        this.bookRepository.save(book);
        // na sekoj save se refreshira materiliziraniot view
        //this.refreshMaterializedView();

        // pri sekoe dodavanje na kniga ke se refresira, nekoj event
        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));

        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new InvalidAuthorIdException(bookDto.getAuthor()));

        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());
        this.bookRepository.save(book);


        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);

    }

    @Override
    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, CategoryType type, Long authorId, Integer avaliableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new InvalidAuthorIdException(authorId));
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new InvalidBookIdException(id));

        book.setName(name);
        book.setCategory(type);
        book.setAuthor(author);
        book.setAvailableCopies(avaliableCopies);

        this.bookRepository.save(book);
        // na sekoj edit se refresira  materiliziraniot view
        //this.refreshMaterializedView();

        // pri sekoe dodavanje na kniga ke se refresira
        //this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);

    }

    @Override
    public Optional<Book> edit(Long id,BookDto bookDto) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new InvalidBookIdException(id));

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new InvalidAuthorIdException(bookDto.getAuthor()));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public void markasTaken(Long bookId) throws BookNoLongerAvailableException {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(()-> new InvalidBookIdException(bookId));
        Integer availableCopies = book.getAvailableCopies();
        if (availableCopies == 0) {
            throw new BookNoLongerAvailableException(book.getName());
        }
        book.setAvailableCopies(availableCopies - 1);
        bookRepository.save(book);
    }
    @Override
    public void refreshMaterializedView() {

        this.booksPerAuthorViewRepository.refreshMaterializedView();
    }

    @Override
    public Page<Book> findAllWithPaggination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

}
