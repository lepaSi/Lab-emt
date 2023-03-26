package mk.ukim.finki.emtaud.model.exceptions;

public class InvalidCountryIdException extends RuntimeException{
    public InvalidCountryIdException(Long id) {
            super(String.format("Category with id: %d is not found", id));

    }
}
