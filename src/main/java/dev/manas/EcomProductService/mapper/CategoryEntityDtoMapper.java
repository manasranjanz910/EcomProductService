package dev.manas.EcomProductService.mapper;

import dev.manas.EcomProductService.dto.CategoryRequestDto;
import dev.manas.EcomProductService.dto.CategoryResponseDto;
import dev.manas.EcomProductService.dto.ProductResponseDto;
import dev.manas.EcomProductService.entity.Category;
import dev.manas.EcomProductService.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryEntityDtoMapper {

    public static CategoryResponseDto convertCategoryEntityToCategoryResponseDto(Category category)
    {

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryName(category.getName());
        categoryResponseDto.setCategoryId(category.getId());
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        if(!(category.getProducts() == null || category.getProducts().isEmpty())) {
            for (Product product : category.getProducts()) {
                productResponseDtos.add(ProductEntityDtoMapper.convertProductEntityToProductResponseDto(product));
            }
        }
        categoryResponseDto.setProducts(productResponseDtos);
        return categoryResponseDto;

    }
    public static Category convertCategoryRequestDtoToEntity (CategoryRequestDto categoryRequestDto)
    {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }
}
