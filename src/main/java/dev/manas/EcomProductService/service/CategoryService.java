package dev.manas.EcomProductService.service;

import dev.manas.EcomProductService.dto.CategoryRequestDto;
import dev.manas.EcomProductService.dto.CategoryResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    public List<CategoryResponseDto> categories();
    public CategoryResponseDto findById(UUID categoryId);
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, UUID categoryId);
    public boolean deleteCategory(UUID categoryId);
    public double getTotalPriceForAllProducts(UUID categoryId);

}
