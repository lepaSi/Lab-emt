package mk.ukim.finki.emtaud.listeners;

import mk.ukim.finki.emtaud.model.events.BookCreatedEvent;
import mk.ukim.finki.emtaud.service.BookService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookEventHandlers {

    private final BookService bookService;

    public BookEventHandlers(BookService bookService) {
        this.bookService = bookService;
    }
    // ocekuva odreden nastan da se sluci
    @EventListener
    public void onBookCreated(BookCreatedEvent event){
        this.bookService.refreshMaterializedView();
    }
}
