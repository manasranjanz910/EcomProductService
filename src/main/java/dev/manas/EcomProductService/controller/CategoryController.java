package dev.manas.EcomProductService.controller;

import dev.manas.EcomProductService.dto.CategoryRequestDto;
import dev.manas.EcomProductService.dto.CategoryResponseDto;
import dev.manas.EcomProductService.exception.InvalidCategoryException;
import dev.manas.EcomProductService.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity <CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequestDto)
    {
         return ResponseEntity.ok(categoryService.createCategory(categoryRequestDto));
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> categories()
    {
        return ResponseEntity.ok(categoryService.categories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto>findById(@PathVariable ("categoryId") UUID categoryId )
    {
        if (categoryId == null)
        {
            throw new InvalidCategoryException("Invalid Category Id "+categoryId);
        }
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity <CategoryResponseDto> updateCategory(@RequestBody CategoryRequestDto categoryRequestDto , @PathVariable UUID categoryId)
    {
        return ResponseEntity.ok(categoryService.updateCategory(categoryRequestDto,categoryId));
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable  UUID categoryId)
    {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }

    @GetMapping ("/totalprice/{categoryId}")
    public ResponseEntity<Double>getTotoalPriceForAllProducts(@PathVariable("categoryId") UUID categoryId)
    {
        return ResponseEntity.ok(categoryService.getTotalPriceForAllProducts(categoryId));
    }






}
