package mk.ukim.finki.emtaud.web.rest;

import mk.ukim.finki.emtaud.model.enumerations.CategoryType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoriesRestController {
    @GetMapping
    public ResponseEntity<List<CategoryType>> findAll() {
        return ResponseEntity.ok().body(List.of(CategoryType.values()));
    }
}
