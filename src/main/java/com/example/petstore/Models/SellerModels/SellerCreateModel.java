package com.example.petstore.Models.SellerModels;

import com.example.petstore.Entities.Sex;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerCreateModel {
    private String Name;
    private Sex Gender;
    private double Salary;
}
