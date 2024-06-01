package dev.manas.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CartResponseDto {

    private UUID cartId;
    private String cartName;
    private List<ProductResponseDto> products;


}
