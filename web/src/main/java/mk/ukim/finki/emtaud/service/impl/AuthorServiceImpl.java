package mk.ukim.finki.emtaud.service.impl;

import mk.ukim.finki.emtaud.model.Author;
import mk.ukim.finki.emtaud.model.Country;
import mk.ukim.finki.emtaud.model.dto.AuthorDto;
import mk.ukim.finki.emtaud.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emtaud.repository.AuthorRepository;
import mk.ukim.finki.emtaud.repository.CountryRepository;
import mk.ukim.finki.emtaud.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long Id) {
        return this.authorRepository.findById(Id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new InvalidCountryIdException(countryId));
        return Optional.of(this.authorRepository.save(new Author(name,surname,country)));
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Author author = this.authorRepository.save(new Author(authorDto.getName(),authorDto.getSurname()));
        return Optional.of(author);
    }

    @Override
    public void delete(Long id) {
        this.authorRepository.deleteById(id);
    }
}
