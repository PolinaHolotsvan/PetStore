package com.example.petstore.Models.ManagerModels;

import com.example.petstore.Entities.Sex;
import lombok.Data;

@Data
public class ManagerBasicModel {
    private String Name;
    private Sex Gender;
    private double Salary;
}
