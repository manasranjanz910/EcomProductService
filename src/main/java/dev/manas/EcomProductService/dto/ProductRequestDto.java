package dev.manas.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductRequestDto {

    private String title;
    private double price;
    private String description;
    private String image ;
    private UUID categoryId;

}
