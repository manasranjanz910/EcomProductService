package dev.manas.EcomProductService.exception;

public class InvalidProductIdException extends RuntimeException{
    public InvalidProductIdException() {
    }
    public InvalidProductIdException(String message) {
        super(message);
    }

}
