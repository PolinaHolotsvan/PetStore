package com.example.petstore.Models.ManagerModels;

import com.example.petstore.Entities.Sex;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerBasicModel {
    private String Name;
    private Sex Gender;
    private double Salary;
}
