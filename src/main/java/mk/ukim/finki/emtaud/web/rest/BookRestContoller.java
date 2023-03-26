package mk.ukim.finki.emtaud.web.rest;

import mk.ukim.finki.emtaud.model.Book;
import mk.ukim.finki.emtaud.model.dto.BookDto;
import mk.ukim.finki.emtaud.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// nema da ni vrakja nekakov view tuku json podatoci, gi rednerira druga aplikacija vo nasiot slucaj react app (koja prakja http baranje do nasava app).
@RestController
@RequestMapping("/api/books")
public class BookRestContoller {

    private final BookService bookService;

    public BookRestContoller(BookService bookService) {
        this.bookService = bookService;
    }

    // read
    @GetMapping
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/pagination")
    public List<Book> findAllWithPaggination(Pageable pagination){
        return this.bookService.findAllWithPaggination(pagination).getContent();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return this.bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // create

    @PostMapping("/add")
    public  ResponseEntity<Book> save(@RequestBody BookDto bookDto){
        return this.bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() ->ResponseEntity.badRequest().build());

    }

    // update

    @PutMapping("/edit/{id}")
    public  ResponseEntity<Book> edit(@PathVariable Long id,@RequestBody BookDto bookDto){
        return this.bookService.edit(id,bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() ->ResponseEntity.badRequest().build());

    }

    // delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.bookService.delete(id);
        if (this.bookService.findById(id).isEmpty())
             return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    // markastaken

    @PutMapping("/markastaken/{id}")
    public ResponseEntity<Void> markAsTaken(@PathVariable Long id) {
        bookService.markasTaken(id);
        return ResponseEntity.ok().build();
    }


}
