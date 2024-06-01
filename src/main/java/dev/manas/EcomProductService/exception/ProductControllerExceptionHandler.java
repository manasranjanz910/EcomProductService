package dev.manas.EcomProductService.exception;

import dev.manas.EcomProductService.controller.ProductController;
import dev.manas.EcomProductService.dto.FakeProductDtos.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ProductController.class)
public class ProductControllerExceptionHandler {
    @ExceptionHandler({ProductNotFoundException.class,ProductsAreNotFoundException.class})
    public ResponseEntity handleNoProductException(ProductSuperException pe)
    {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                pe.getMessage(),404
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity handleInvalidProductIdException(InvalidProductIdException pe)
    {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                pe.getMessage(),400
        );
        return new ResponseEntity<>(exceptionResponseDto,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(TestApiNotWorkingException.class)
    public ResponseEntity handleTestApiNotWorkingException(TestApiNotWorkingException te)
    {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                te.getMessage(),400
        );
        return new ResponseEntity<>(exceptionResponseDto,HttpStatus.BAD_REQUEST);

    }




}
