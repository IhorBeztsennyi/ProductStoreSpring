package ua.goit.exceptions;

public class ProductIsAlreadyExistsException extends RuntimeException {
    public ProductIsAlreadyExistsException(String message) {
        super(message);
    }

    public ProductIsAlreadyExistsException() {
        super();
    }
}
