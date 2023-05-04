package com.example.petstore.models.goodsModels;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsBasicModel {
    @NotEmpty(message = "This field can not be empty")
    private String name;
    @NotEmpty(message = "This field can not be empty")
    private String category;
    @Min(value = 0, message = "The price can not be negative")
    private double price;
}
