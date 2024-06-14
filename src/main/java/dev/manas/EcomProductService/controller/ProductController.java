package dev.manas.EcomProductService.controller;

import dev.manas.EcomProductService.dto.ProductRequestDto;
import dev.manas.EcomProductService.dto.ProductResponseDto;
import dev.manas.EcomProductService.entity.Product;
import dev.manas.EcomProductService.exception.CatagoryNotFoundException;
import dev.manas.EcomProductService.exception.InvalidProductIdException;
import dev.manas.EcomProductService.exception.ProductNotFoundException;
import dev.manas.EcomProductService.exception.TestApiNotWorkingException;
import dev.manas.EcomProductService.mapper.ProductEntityDtoMapper;
import dev.manas.EcomProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    @Qualifier("productservice")
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct ( @RequestBody ProductRequestDto productRequestDto)throws CatagoryNotFoundException
    {
        return ResponseEntity.ok(productService.createProduct(productRequestDto));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductByID (@PathVariable("productId") UUID productId )throws ProductNotFoundException
    {
        if (productId == null)
        {
            throw new InvalidProductIdException("Product Does not exists with productId"+productId);
        }
       ProductResponseDto product = productService.getProductById(productId);
       return ResponseEntity.ok(product);
    }
    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts()
    {
        List<ProductResponseDto> savedProducts = productService.getAllProducts();
        return ResponseEntity.ok(savedProducts);
    }
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto updatedProduct , @PathVariable("productId") UUID productId)
    {
       return ResponseEntity.ok(productService.updatedProduct(updatedProduct,productId));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity <Boolean>deleteProduct(@PathVariable UUID productId)
    {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }

    @GetMapping("/{minPrice}/{maxPrice}")
    public ResponseEntity<List<ProductResponseDto>> getAllProductsBetweenMinAndMaxPrice(@PathVariable("minPrice") double minPrice, @PathVariable("maxPrice")double maxPrice)
    {
        return ResponseEntity.ok(productService.findByPriceBetween(minPrice,maxPrice));
    }

    //used for demo of controller advice
    @GetMapping("/testProduct/{id}")
    public String test(@PathVariable("id") int id )
    {
        if (id < 1 || id > 8 )
        {
            throw new TestApiNotWorkingException("Test Api does not work for invalid Id "+ id );
        }
        return "Hello User ";
    }




}
