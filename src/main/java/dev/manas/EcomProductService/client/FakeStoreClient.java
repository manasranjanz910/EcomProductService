package dev.manas.EcomProductService.client;


import dev.manas.EcomProductService.dto.FakeProductDtos.CartResponseDto;
import dev.manas.EcomProductService.dto.FakeProductDtos.FakeProductResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class FakeStoreClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${FakeProduct.BaseURL}")
    private String baseUrl;
    @Value("${FakeProduct.ProductKey}")
    private String apiKey;
    @Value("${FakeProduct.productId}")
    private String productId;
    @Value("${FakeStore.getCart}")
    private String getCart;

    public List<FakeProductResponseDto> getAllProducts()
    {
        String getAllProductUrl = baseUrl.concat(apiKey);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductResponseDto[]>productList = restTemplate.getForEntity(getAllProductUrl, FakeProductResponseDto[].class);
        return List.of(productList.getBody());

    }
    public FakeProductResponseDto  getProductByID()
    {
        String getProductByID = baseUrl.concat(apiKey).concat(productId);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeProductResponseDto>product = restTemplate.getForEntity(getProductByID, FakeProductResponseDto.class);
        return product.getBody();
    }
    public FakeProductResponseDto getProductById(int id)
    {
        String fakeStoreGetProductUrl = baseUrl.concat(apiKey).concat("/"+id);
        RestTemplate restTemplate =  restTemplateBuilder.build();
        ResponseEntity <FakeProductResponseDto> product = restTemplate.getForEntity(fakeStoreGetProductUrl, FakeProductResponseDto.class);
        return product.getBody();
    }
    //'https://fakestoreapi.com/products/1

    public CartResponseDto getCartById(int cartid)
    {
        String fakeStoreCartUrl = baseUrl.concat(getCart).concat("/"+cartid);
        RestTemplate restTemplate =  restTemplateBuilder.build();
        ResponseEntity <CartResponseDto> cart= restTemplate.getForEntity(fakeStoreCartUrl, CartResponseDto.class);
        return cart.getBody();
    }

}
