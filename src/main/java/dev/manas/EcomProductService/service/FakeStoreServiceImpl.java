package dev.manas.EcomProductService.service;

import dev.manas.EcomProductService.client.FakeStoreClient;
import dev.manas.EcomProductService.dto.FakeProductDtos.FakeProductResponseDto;
import dev.manas.EcomProductService.entity.Product;
import dev.manas.EcomProductService.exception.ProductNotFoundException;
import dev.manas.EcomProductService.exception.ProductsAreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakestoreproductservice")
public class FakeStoreServiceImpl{

    @Autowired
    private FakeStoreClient productClient;

    public List<FakeProductResponseDto> getAllProducts() throws ProductNotFoundException {
        List<FakeProductResponseDto> products = productClient.getAllProducts();
        if (products == null)
        {
            throw new ProductsAreNotFoundException("products are not found ");
        }
        return products;
    }

    public FakeProductResponseDto getProductByID() {
       FakeProductResponseDto fakeProductResponseDto = productClient.getProductByID();
       return fakeProductResponseDto;
    }

    public FakeProductResponseDto getProductById(int id) throws ProductNotFoundException {
        FakeProductResponseDto product = productClient.getProductById(id);
        if (product == null)
        {
            throw new ProductNotFoundException("product not found for id "+id);
        }
        return product;

    }

    public Product createProduct(Product product) {
        return null;
    }

}
