package dev.manas.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductResponseDto {
    private UUID productId;
    private String title;
    private double price;
    private String description;
    private String catagory;
    private String image ;
    private double rating;
}
