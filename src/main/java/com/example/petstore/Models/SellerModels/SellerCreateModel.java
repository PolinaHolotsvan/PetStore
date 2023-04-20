package com.example.petstore.Models.SellerModels;

import com.example.petstore.Entities.Sex;
import lombok.Data;

@Data
public class SellerCreateModel {
    private String Name;
    private Sex Gender;
    private double Salary;
}
