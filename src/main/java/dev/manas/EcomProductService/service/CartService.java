package dev.manas.EcomProductService.service;


import dev.manas.EcomProductService.dto.CartRequestDto;
import dev.manas.EcomProductService.dto.CartResponseDto;

import java.util.UUID;

public interface CartService {

    CartResponseDto createCart(CartRequestDto cartRequestDto);
    CartResponseDto getById(UUID cartId);
}
