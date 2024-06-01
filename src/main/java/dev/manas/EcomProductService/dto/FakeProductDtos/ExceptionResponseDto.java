package dev.manas.EcomProductService.dto.FakeProductDtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponseDto {
    private String message;
    private int code;

    public ExceptionResponseDto(String message, int statuscode) {
        this.message = message;
        this.code = statuscode;
    }
}
