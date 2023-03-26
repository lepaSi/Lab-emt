package mk.ukim.finki.emtaud.model.exceptions;

public class InvalidBookIdException extends RuntimeException{

    public InvalidBookIdException(Long id) {
        super(String.format("Category with id: %d is not found", id));
    }

}
