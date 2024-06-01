package dev.manas.EcomProductService.service;


import dev.manas.EcomProductService.dto.CategoryRequestDto;
import dev.manas.EcomProductService.dto.CategoryResponseDto;
import dev.manas.EcomProductService.entity.Category;
import dev.manas.EcomProductService.entity.Product;
import dev.manas.EcomProductService.exception.InvalidCategoryIdException;
import dev.manas.EcomProductService.mapper.CategoryEntityDtoMapper;
import dev.manas.EcomProductService.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteProduct ()
    {
        UUID categoryId = UUID.randomUUID();
        boolean actual = categoryServiceImpl.deleteCategory(categoryId);
        boolean expected  = true;
        Assertions.assertEquals(actual ,expected );
    }

    @Test
    public void testTotalSumForAllProducts()
    {
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryOptionalMockData = getCategoryMockData();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        double actualCost = categoryServiceImpl.getTotalPriceForAllProducts(categoryId);
        double expectedCost = 60000.00;
        Assertions.assertEquals(actualCost,expectedCost);

    }
    @Test
    public void testTotalSumForZeroProductsUnderCategory()
    {
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryWithZeroProducts = getCategoryMockDataWithZeroProducts();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryWithZeroProducts);
        double expected = 0.00;
        double actual = categoryServiceImpl.getTotalPriceForAllProducts(categoryId);

        //verify
        Assertions.assertEquals(expected,actual);

    }
    @Test
    public void testInvalidCategoryIdException()
    {
        UUID categoryId = UUID.randomUUID();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        Assertions.assertThrows(InvalidCategoryIdException.class,()->categoryServiceImpl.getTotalPriceForAllProducts(categoryId));
    }

    private  Optional<Category> getCategoryMockDataWithZeroProducts()
    {
        Category  category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("CategoryName");
        List<Product> products = new ArrayList<>();
        category.setProducts(products);
        return Optional.of(category);

    }

    private Optional<Category> getCategoryMockData()
    {
       Category  category = new Category();
       category.setId(UUID.randomUUID());
       category.setName("CategoryName");

       Product product1 = new Product();
       product1.setTitle("product");
       product1.setDescription("product desc");
       product1.setPrice(30000);
       product1.setCatagory(category);

       Product product2 = new Product();
       product2.setTitle("product");
       product2.setDescription("product desc");
       product2.setPrice(20000);
       product2.setCatagory(category);

       Product product3 = new Product();
       product3.setTitle("product");
       product3.setDescription("product desc");
       product3.setPrice(10000);
       product3.setCatagory(category);

       List<Product> products = new ArrayList<>();
       products.add(product1);
       products.add(product2);
       products.add(product3);

       category.setProducts(products);
       return Optional.of(category);
    }






    }











