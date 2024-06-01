package dev.manas.EcomProductService.dto.FakeProductDtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CartResponseDto {
    private int id;
    private int userId;
    private LocalDateTime date;
    private List<ProductsResponseDto> products;
    private int __v = 0;
    /*
    {
    "id": 5,
    "userId": 3,
    "date": "2020-03-01T00:00:00.000Z",
    "products": [
        {
            "productId": 7,
            "quantity": 1
        },
        {
            "productId": 8,
            "quantity": 1
        }
    ],
    "__v": 0
}
     */
}
