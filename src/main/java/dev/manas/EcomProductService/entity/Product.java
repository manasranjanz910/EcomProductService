package dev.manas.EcomProductService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    @ManyToOne()
    private Category catagory;
    private String image ;
    private double rating;
    @ManyToOne()
    @JoinColumn(name="product_id")
    private Cart cart;
}
