package dev.manas.EcomProductService.exception;

public class ProductNotFoundException extends ProductSuperException{

    public ProductNotFoundException(String message )
    {
        super(message);
    }
}
