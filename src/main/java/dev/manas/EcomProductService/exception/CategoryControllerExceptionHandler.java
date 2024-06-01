package dev.manas.EcomProductService.exception;

import dev.manas.EcomProductService.controller.CategoryController;
import dev.manas.EcomProductService.dto.FakeProductDtos.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = CategoryController.class)
public class CategoryControllerExceptionHandler {
    @ExceptionHandler({InvalidCategoryException.class,InvalidCategoryIdException.class})
    public ResponseEntity handleInvalidCategoryException(CategorySuperException ce)
    {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                ce.getMessage(),400
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }
}
