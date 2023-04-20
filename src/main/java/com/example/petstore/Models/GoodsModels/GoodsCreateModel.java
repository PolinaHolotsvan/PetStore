package com.example.petstore.Models.GoodsModels;

import lombok.Data;

@Data
public class GoodsCreateModel {
    private String Name;
    private String Category;
    private double Price;
}
