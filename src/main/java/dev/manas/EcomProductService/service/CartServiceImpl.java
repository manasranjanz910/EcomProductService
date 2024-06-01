package dev.manas.EcomProductService.service;

import dev.manas.EcomProductService.dto.CartRequestDto;
import dev.manas.EcomProductService.dto.CartResponseDto;
import dev.manas.EcomProductService.entity.Cart;
import dev.manas.EcomProductService.entity.Product;
import dev.manas.EcomProductService.exception.CartNotFoundException;
import dev.manas.EcomProductService.mapper.CartEntityDtoMapper;
import dev.manas.EcomProductService.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;


    @Override
    public CartResponseDto createCart(CartRequestDto cartRequestDto) {
         Cart cart = CartEntityDtoMapper.convertCartRequestDtoToCart(cartRequestDto);
         cart.setCartName(cartRequestDto.getCartName());
         Cart savedCart = cartRepository.save(cart);
         return CartEntityDtoMapper.convertCartToCartResponseDto(savedCart);
    }

    @Override
    public CartResponseDto getById(UUID cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                ()->new CartNotFoundException("cart not found for invalid id "+cartId)
        );
        return CartEntityDtoMapper.convertCartToCartResponseDto(cart);

    }
}
