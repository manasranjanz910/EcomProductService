package dev.manas.EcomProductService.controller;


import dev.manas.EcomProductService.dto.CategoryRequestDto;
import dev.manas.EcomProductService.dto.CategoryResponseDto;
import dev.manas.EcomProductService.entity.Category;
import dev.manas.EcomProductService.entity.Product;
import dev.manas.EcomProductService.exception.CategoryNotFoundException;
import dev.manas.EcomProductService.exception.InvalidCategoryException;
import dev.manas.EcomProductService.service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import static org.mockito.Mockito.when;

public class CategoryControllerTest {
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateCategorySuccess()
    {
        //arrange
        UUID categoryId = UUID.randomUUID();
        CategoryRequestDto updateRequestDto = new CategoryRequestDto();
        updateRequestDto.setName("category1");
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryId(categoryId);
        categoryResponseDto.setCategoryName("category1");
        when(categoryService.updateCategory(updateRequestDto,categoryId)).thenReturn(categoryResponseDto);
        //act
        ResponseEntity<CategoryResponseDto> categoryResponseEntity = categoryController.updateCategory(updateRequestDto,categoryId);
        //assert
        Assertions.assertEquals(categoryResponseEntity.getBody(),categoryResponseDto);

    }

    @Test
    public void testUpdateCategoryFailure()
    {
        //arrange
        UUID categoryId = UUID.randomUUID();
        CategoryRequestDto updateRequestDto = new CategoryRequestDto();
        updateRequestDto.setName("category1");

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryId(categoryId);
        categoryResponseDto.setCategoryName("");
        when(categoryService.updateCategory(updateRequestDto,categoryId)).thenReturn(categoryResponseDto);
        //act
        ResponseEntity<CategoryResponseDto> categoryResponseEntity = categoryController.updateCategory(updateRequestDto,categoryId);
        //assert
        Assertions.assertEquals(categoryResponseEntity.getBody(),categoryResponseDto);
    }


    @Test
    void findById_withValidId_shouldReturnCategory() {
        // Arrange
        UUID validCategoryId = UUID.randomUUID();
        CategoryResponseDto mockResponse = new CategoryResponseDto();
        Mockito.when(categoryService.findById(validCategoryId)).thenReturn(mockResponse);

        // Act
        ResponseEntity<CategoryResponseDto> response = categoryController.findById(validCategoryId);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(mockResponse);
        verify(categoryService, times(1)).findById(validCategoryId);
    }

    @Test
    void findById_withNullId_shouldThrowInvalidCategoryException() {
        // Arrange
        UUID invalidCategoryId = null;
        // invalidCategoryId is null

        // Act & Assert
        assertThatThrownBy(() -> categoryController.findById(invalidCategoryId))
                .isInstanceOf(InvalidCategoryException.class)
                .hasMessageContaining("Invalid Category Id " + invalidCategoryId);

        verify(categoryService, never()).findById(any());
    }

    @Test
    void deleteCategory_withValidId_shouldReturnTrue() {
        // Arrange
        UUID validCategoryId = UUID.randomUUID();
        when(categoryService.deleteCategory(validCategoryId)).thenReturn(true);

        // Act
        ResponseEntity<Boolean> response = categoryController.deleteCategory(validCategoryId);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isTrue();
        verify(categoryService, times(1)).deleteCategory(validCategoryId);
    }

    @Test
    void deleteCategory_withNonExistentId_shouldReturnFalse() {
        // Arrange
        UUID validCategoryId = UUID.randomUUID();
        when(categoryService.deleteCategory(validCategoryId)).thenReturn(false);

        // Act
        ResponseEntity<Boolean> response = categoryController.deleteCategory(validCategoryId);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isFalse();
        verify(categoryService, times(1)).deleteCategory(validCategoryId);
    }

    @Test
    void categories_shouldReturnCategoryList() {
        // Arrange
        List<CategoryResponseDto> categoryList;
        CategoryResponseDto category1 = new CategoryResponseDto();
        CategoryResponseDto category2 = new CategoryResponseDto();
        categoryList = Arrays.asList(category1, category2);
        when(categoryService.categories()).thenReturn(categoryList);

        // Act
        ResponseEntity<List<CategoryResponseDto>> response = categoryController.categories();

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(categoryList);
        verify(categoryService, times(1)).categories();
    }

    @Test
    void categories_whenNoCategories_shouldReturnEmptyList() {
        // Arrange
        when(categoryService.categories()).thenReturn(Arrays.asList());

        // Act
        ResponseEntity<List<CategoryResponseDto>> response = categoryController.categories();

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEmpty();
        verify(categoryService, times(1)).categories();
    }

    @Test
    void createCategory_shouldReturnCreatedCategory() {
        CategoryRequestDto categoryRequestDto;
        CategoryResponseDto categoryResponseDto;
        categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setName("New Category");

        categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryId(UUID.randomUUID());
        categoryResponseDto.setCategoryName("New Category");
        // Arrange
        when(categoryService.createCategory(categoryRequestDto)).thenReturn(categoryResponseDto);

        // Act
        ResponseEntity<CategoryResponseDto> response = categoryController.createCategory(categoryRequestDto);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(categoryResponseDto);
        verify(categoryService, times(1)).createCategory(categoryRequestDto);
    }

    @Test
    void createCategory_withInvalidRequest_shouldHandleGracefully() {
        // Arrange
        CategoryRequestDto invalidRequestDto = new CategoryRequestDto(); // Assume this is invalid
        when(categoryService.createCategory(invalidRequestDto)).thenThrow(new IllegalArgumentException("Invalid request"));

        // Act & Assert
        assertThatThrownBy(() -> categoryController.createCategory(invalidRequestDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid request");

        verify(categoryService, times(1)).createCategory(invalidRequestDto);
    }
    @Test
    void getTotalPriceForAllProducts_withValidId_shouldReturnTotalPrice() {
        // Arrange
        UUID validCategoryId = UUID.randomUUID();
        double expectedTotalPrice = 123.45;
        when(categoryService.getTotalPriceForAllProducts(validCategoryId)).thenReturn(expectedTotalPrice);

        // Act
        ResponseEntity<Double> response = categoryController.getTotoalPriceForAllProducts(validCategoryId);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedTotalPrice);
        verify(categoryService, times(1)).getTotalPriceForAllProducts(validCategoryId);
    }

    @Test
    void getTotalPriceForAllProducts_withInvalidId_shouldHandleGracefully() {
        // Arrange
        UUID invalidCategoryId = null;
        // Assuming the service layer throws an IllegalArgumentException for an invalid ID
        when(categoryService.getTotalPriceForAllProducts(invalidCategoryId)).thenThrow(new IllegalArgumentException("Invalid category ID"));

        // Act & Assert
        assertThatThrownBy(() -> categoryController.getTotoalPriceForAllProducts(invalidCategoryId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid category ID");

        verify(categoryService, times(1)).getTotalPriceForAllProducts(invalidCategoryId);
    }















}
