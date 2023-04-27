package com.example.petstore.Models.GoodsModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsBasicModel {
    private String Name;
    private String Category;
    private double Price;
}
