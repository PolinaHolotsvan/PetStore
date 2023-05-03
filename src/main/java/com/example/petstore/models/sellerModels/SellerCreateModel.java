package com.example.petstore.models.sellerModels;

import com.example.petstore.entities.Sex;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerCreateModel {
    private String name;
    private Sex gender;
    private double salary;
}
