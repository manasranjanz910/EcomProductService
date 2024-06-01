package dev.manas.EcomProductService.service;

import dev.manas.EcomProductService.dto.ProductRequestDto;
import dev.manas.EcomProductService.dto.ProductResponseDto;
import dev.manas.EcomProductService.entity.Product;
import dev.manas.EcomProductService.exception.CatagoryNotFoundException;
import dev.manas.EcomProductService.exception.InvalidProductIdException;
import dev.manas.EcomProductService.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public ProductResponseDto createProduct(ProductRequestDto product) throws CatagoryNotFoundException;
    public List<ProductResponseDto> getAllProducts() ;
    public ProductResponseDto getProductById(UUID productId)throws InvalidProductIdException;
    public ProductResponseDto updatedProduct (ProductRequestDto updatedProduct , UUID productId);
    public boolean deleteProduct(UUID id);
    public ProductResponseDto findProductByTitle(String title);
    public List<ProductResponseDto>findByPriceBetween(double minPrice,double maxPrice);

}
