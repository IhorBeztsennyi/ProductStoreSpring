package ua.goit.exceptions;

public class VendorIsAlreadyExistsException extends RuntimeException{

    public VendorIsAlreadyExistsException(String message) {
        super(message);
    }

    public VendorIsAlreadyExistsException() {
        super();
    }
}
