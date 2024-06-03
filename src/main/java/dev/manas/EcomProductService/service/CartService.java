package dev.manas.EcomProductService.service;


import dev.manas.EcomProductService.dto.CartRequestDto;
import dev.manas.EcomProductService.dto.CartResponseDto;
import dev.manas.EcomProductService.entity.Product;

import java.util.UUID;

public interface CartService {

    CartResponseDto createCart(CartRequestDto cartRequestDto);
    CartResponseDto getById(UUID cartId);
    double calTotalSumOfProducts(UUID cart_Id);

}
