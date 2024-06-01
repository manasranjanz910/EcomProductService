package dev.manas.EcomProductService.mapper;


import dev.manas.EcomProductService.dto.CartRequestDto;
import dev.manas.EcomProductService.dto.CartResponseDto;
import dev.manas.EcomProductService.dto.ProductResponseDto;
import dev.manas.EcomProductService.entity.Cart;
import dev.manas.EcomProductService.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CartEntityDtoMapper {

    public static CartResponseDto convertCartToCartResponseDto(Cart cart)
    {
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setCartId(cart.getId());
        cartResponseDto.setCartName("Cart");
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        if(!(cart.getProducts() == null || cart.getProducts().isEmpty())) {
            for (Product product : cart.getProducts()) {
                productResponseDtos.add(ProductEntityDtoMapper.convertProductEntityToProductResponseDto(product));
            }
        }
        cartResponseDto.setProducts(productResponseDtos);
        return cartResponseDto;

    }
    public static Cart convertCartRequestDtoToCart(CartRequestDto cartRequestDto)
    {
        Cart cart = new Cart();
        cart.setCartName(cartRequestDto.getCartName());
        return cart;
    }
}
