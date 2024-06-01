package dev.manas.EcomProductService.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String message)
    {
        super(message);
    }

}
