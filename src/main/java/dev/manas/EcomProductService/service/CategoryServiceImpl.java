package dev.manas.EcomProductService.service;

import dev.manas.EcomProductService.dto.CategoryRequestDto;
import dev.manas.EcomProductService.dto.CategoryResponseDto;
import dev.manas.EcomProductService.dto.ProductResponseDto;
import dev.manas.EcomProductService.entity.Category;
import dev.manas.EcomProductService.entity.Product;
import dev.manas.EcomProductService.exception.CategoryNotFoundException;
import dev.manas.EcomProductService.exception.InvalidCategoryException;
import dev.manas.EcomProductService.exception.InvalidCategoryIdException;
import dev.manas.EcomProductService.mapper.CategoryEntityDtoMapper;
import dev.manas.EcomProductService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl implements CategoryService {
   @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        Category savedCategory = CategoryEntityDtoMapper.convertCategoryRequestDtoToEntity(categoryRequestDto);
        savedCategory = categoryRepository.save(savedCategory);
        return CategoryEntityDtoMapper.convertCategoryEntityToCategoryResponseDto(savedCategory);
    }

    @Override
    public List<CategoryResponseDto> categories() {
         List<Category>categoryList = categoryRepository.findAll();
         List<CategoryResponseDto> categoryResponseDtoList = new ArrayList<>();
         for (Category c : categoryList)
         {
             categoryResponseDtoList.add(CategoryEntityDtoMapper.convertCategoryEntityToCategoryResponseDto(c));
         }
         return categoryResponseDtoList;
    }

    @Override
    public CategoryResponseDto findById(UUID categoryId) {
        Category category  = categoryRepository.findById(categoryId).orElseThrow(
                ()->new CategoryNotFoundException("Category Not Found Following Id "+categoryId)
        );
        return CategoryEntityDtoMapper.convertCategoryEntityToCategoryResponseDto(category);
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, UUID categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(
                ()->new InvalidCategoryException("Category does not exists for categoryId"+categoryId)
        );
        savedCategory.setName(categoryRequestDto.getName());
        savedCategory = categoryRepository.save(savedCategory);
        return CategoryEntityDtoMapper.convertCategoryEntityToCategoryResponseDto(savedCategory);
    }

    @Override
    public boolean deleteCategory(UUID categoryId) {
        categoryRepository.deleteById(categoryId);
        return true;
    }

    @Override
    public double getTotalPriceForAllProducts(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()->new InvalidCategoryIdException("Category doesn't exists for the given Id"+categoryId)
        );
        long countOfProducts =  category.getProducts().stream().count();
        if (countOfProducts == 0) {
            return 0;
        }
        return category.getProducts().stream()
                .mapToDouble(Product :: getPrice)
                .sum();
    }




}