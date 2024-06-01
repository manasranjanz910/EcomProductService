package dev.manas.EcomProductService.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String message )
    {
        super(message);
    }

}
