package dev.manas.EcomProductService.exception;

public class InvalidCategoryIdException extends RuntimeException{

    public InvalidCategoryIdException (String message)
    {
        super(message);
    }
}
