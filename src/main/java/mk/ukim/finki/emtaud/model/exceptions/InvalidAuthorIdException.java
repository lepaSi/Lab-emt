package mk.ukim.finki.emtaud.model.exceptions;

public class InvalidAuthorIdException  extends RuntimeException{
    public InvalidAuthorIdException(Long id) {

            super(String.format("Category with id: %d is not found", id));

    }
}
