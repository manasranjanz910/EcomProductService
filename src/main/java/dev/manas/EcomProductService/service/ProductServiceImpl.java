package dev.manas.EcomProductService.service;

import dev.manas.EcomProductService.dto.ProductRequestDto;
import dev.manas.EcomProductService.dto.ProductResponseDto;
import dev.manas.EcomProductService.entity.Cart;
import dev.manas.EcomProductService.entity.Category;
import dev.manas.EcomProductService.entity.Product;
import dev.manas.EcomProductService.exception.*;
import dev.manas.EcomProductService.mapper.ProductEntityDtoMapper;
import dev.manas.EcomProductService.repository.CartRepository;
import dev.manas.EcomProductService.repository.CategoryRepository;
import dev.manas.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("productservice")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository catagoryRepository;

    @Autowired
    private CartRepository cartRepository;


    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws CatagoryNotFoundException {


            // Find the category, throw exception if not found
            Category savedCategory = catagoryRepository.findById(productRequestDto.getCategoryId()).orElseThrow(
                    () -> new CatagoryNotFoundException("Category Not Found For CategoryId: " + productRequestDto.getCategoryId())
            );

            // Find the cart, throw exception if not found
            Cart savedCart = cartRepository.findById(productRequestDto.getCart_id()).orElseThrow(
                    () -> new CartNotFoundException("Cart Not Found For Id: " + productRequestDto.getCart_id())
            );

            // Convert and save the product
            Product savedProduct = ProductEntityDtoMapper.convertProductRequestDtoToProduct(productRequestDto);
            savedProduct.setCatagory(savedCategory);
            savedProduct.setCart(savedCart);
            savedProduct = productRepository.save(savedProduct);

            // Convert and return the response DTO
            return ProductEntityDtoMapper.convertProductEntityToProductResponseDto(savedProduct);

    }

    @Cacheable(value = "products")
    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product>savedProducts = productRepository.findAll();
        List<ProductResponseDto>products = new ArrayList<>();
        for ( Product product : savedProducts)
        {

            products.add(ProductEntityDtoMapper.convertProductEntityToProductResponseDto(product));
        }
        return products;
    }
    @Cacheable(value = "product",key = "#productId")
    @Override
    public ProductResponseDto getProductById(UUID productId) throws InvalidProductIdException {
        Product savedProduct = productRepository.findById(productId).orElseThrow(
                ()->new ProductNotFoundException("Product Not Found for id :"+productId)
        );
        return ProductEntityDtoMapper.convertProductEntityToProductResponseDto(savedProduct);

    }
    @Override
    public ProductResponseDto updatedProduct(ProductRequestDto updatedProduct, UUID productId) {
        Product savedProduct = productRepository.findById(productId).orElseThrow(
                ()->new ProductNotFoundException("Product Not Found for id :"+productId)
        );

        savedProduct.setImage(updatedProduct.getImage());
        savedProduct.setDescription(updatedProduct.getDescription());
        savedProduct.setPrice(updatedProduct.getPrice());
        savedProduct.setTitle(updatedProduct.getTitle());
        productRepository.save(savedProduct);
        return ProductEntityDtoMapper.convertProductEntityToProductResponseDto(savedProduct);
    }

    @Override
    public boolean deleteProduct(UUID id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public ProductResponseDto findProductByTitle(String title) {
        Product savedProduct =  productRepository.findProductByTitle(title);
        return ProductEntityDtoMapper.convertProductEntityToProductResponseDto(savedProduct);
    }

    @Override
    public List<ProductResponseDto> findByPriceBetween(double minPrice, double maxPrice) {
        List<Product> savedProducts = productRepository.findByPriceBetween(minPrice,maxPrice);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for (Product product : savedProducts)
        {
            productResponseDtos.add(ProductEntityDtoMapper.convertProductEntityToProductResponseDto(product));
        }
        return productResponseDtos;
    }


}
