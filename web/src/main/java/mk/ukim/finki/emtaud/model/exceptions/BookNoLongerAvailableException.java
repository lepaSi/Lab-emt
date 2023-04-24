package mk.ukim.finki.emtaud.model.exceptions;

public class BookNoLongerAvailableException extends RuntimeException {

    public BookNoLongerAvailableException(String name) {
        super(String.format("Book with name %s is no longer available", name));
    }
}