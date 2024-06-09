package dev.manas.EcomProductService.mapper;

import dev.manas.EcomProductService.dto.ProductRequestDto;
import dev.manas.EcomProductService.dto.ProductResponseDto;
import dev.manas.EcomProductService.entity.Cart;
import dev.manas.EcomProductService.entity.Product;

import java.util.UUID;

public class ProductEntityDtoMapper {


    public static ProductResponseDto convertProductEntityToProductResponseDto(Product product)
    {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setCatagory(product.getCatagory().getName());
        productResponseDto.setTitle(product.getTitle());
        productResponseDto.setImage(product.getImage());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setRating(product.getRating());
        productResponseDto.setProductId(product.getId());

        return productResponseDto;
    }
    public static Product convertProductRequestDtoToProduct(ProductRequestDto productRequestDto)
    {
        Product product = new Product();

        product.setTitle(productRequestDto.getTitle());
        product.setRating(0);
        product.setDescription(product.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setImage(productRequestDto.getImage());
        product.setDescription(productRequestDto.getDescription());
        return product;


    }
}
