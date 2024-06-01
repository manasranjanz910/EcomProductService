package dev.manas.EcomProductService.controller;

import dev.manas.EcomProductService.dto.CartRequestDto;
import dev.manas.EcomProductService.dto.CartResponseDto;
import dev.manas.EcomProductService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponseDto>createCart(@RequestBody CartRequestDto cartRequestDto)
    {
        return ResponseEntity.ok(cartService.createCart(cartRequestDto));
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponseDto>getCartById(@PathVariable UUID cartId)
    {
        return ResponseEntity.ok(cartService.getById(cartId));
    }

}
