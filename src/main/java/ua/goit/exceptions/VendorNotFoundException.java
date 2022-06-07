package ua.goit.exceptions;

public class VendorNotFoundException extends RuntimeException {

    public VendorNotFoundException(String message) {
        super(message);
    }

    public VendorNotFoundException() {
        super();
    }
}
