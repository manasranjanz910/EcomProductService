package dev.manas.EcomProductService.service;

import dev.manas.EcomProductService.dto.CartRequestDto;
import dev.manas.EcomProductService.dto.CartResponseDto;
import dev.manas.EcomProductService.entity.Cart;
import dev.manas.EcomProductService.entity.Product;
import dev.manas.EcomProductService.exception.CartNotFoundException;
import dev.manas.EcomProductService.exception.InvalidProductIdException;
import dev.manas.EcomProductService.mapper.CartEntityDtoMapper;
import dev.manas.EcomProductService.repository.CartRepository;
import dev.manas.EcomProductService.repository.ProductRepository;
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
        if (cart.getProducts() != null) {
            for (Product product : cart.getProducts()) {
                product.setCart(cart);
            }
        }
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

    @Override
    public double calTotalSumOfProducts(UUID cart_Id) {
        Cart cart = cartRepository.findById(cart_Id).orElseThrow(

                        ()-> new CartNotFoundException("cart not found for cart_id"+cart_Id)
        );
        double sum = 0;
        if (cart.getProducts().stream().count() == 0)
        {
            sum =0.0;
        }
        else if (cart.getProducts().stream().count() == 1)
        {
            for (Product product : cart.getProducts())
            {
                sum = product.getPrice();
            }
        }
        else
        {
           sum = cart.getProducts().stream().mapToDouble(Product :: getPrice).sum();
        }
        return sum ;

    }


}
