package mk.ukim.finki.emtaud.web.rest;


import mk.ukim.finki.emtaud.model.Author;
import mk.ukim.finki.emtaud.model.Country;
import mk.ukim.finki.emtaud.model.dto.AuthorDto;
import mk.ukim.finki.emtaud.model.dto.CountryDto;
import mk.ukim.finki.emtaud.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll(){
        return this.countryService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        return this.countryService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public  ResponseEntity<Country> save(@RequestBody CountryDto countryDto){
        return this.countryService.save(countryDto)
                .map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() ->ResponseEntity.badRequest().build());

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.countryService.delete(id);
        if (this.countryService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }



}
