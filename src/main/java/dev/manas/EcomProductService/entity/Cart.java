package dev.manas.EcomProductService.entity;

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
    @OneToMany(mappedBy = "cart",fetch = FetchType.EAGER)
    private List<Product> products;

}
