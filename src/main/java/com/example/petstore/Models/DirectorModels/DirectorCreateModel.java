package com.example.petstore.Models.DirectorModels;

import com.example.petstore.Entities.Sex;
import lombok.Data;

@Data
public class DirectorCreateModel {
    private String Name;
    private Sex Gender;
    private double Salary;
}
