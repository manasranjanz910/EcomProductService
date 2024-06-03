package dev.manas.EcomProductService.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Cart extends BaseModel{

    private String cartName;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "cart")
    private List<Product> products;

}
