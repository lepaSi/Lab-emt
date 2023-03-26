package mk.ukim.finki.emtaud.service;

import mk.ukim.finki.emtaud.model.Book;
import mk.ukim.finki.emtaud.model.Country;
import mk.ukim.finki.emtaud.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> save(String name, String continent);

    Optional<Country>save(CountryDto countryDto);

   Optional <Country> update(Long id, String name, String continent);

    void delete(Long id);

    Optional<Country> findById(Long id);


    List<Country> findAll();


}
