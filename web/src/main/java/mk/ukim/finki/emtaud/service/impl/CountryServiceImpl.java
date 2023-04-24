package mk.ukim.finki.emtaud.service.impl;

import mk.ukim.finki.emtaud.model.Country;
import mk.ukim.finki.emtaud.model.dto.CountryDto;
import mk.ukim.finki.emtaud.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.emtaud.repository.CountryRepository;
import mk.ukim.finki.emtaud.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> save(String name, String continent) {


        Country country = new Country(name,continent);
        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        Country country = new Country(countryDto.getName(),countryDto.getContinent());
        this.countryRepository.save(country);

        return Optional.of(country);
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {


        Country country = this.countryRepository.findById(id).orElseThrow(() -> new InvalidCountryIdException(id));

        country.setName(name);
        country.setContinent(continent);

       // country = new Country(name,continent);
        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public void delete(Long id) {

        this.countryRepository.deleteById(id);
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }
}
