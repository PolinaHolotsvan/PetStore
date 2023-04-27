package com.example.petstore.Models.DirectorModels;

import com.example.petstore.Entities.Sex;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectorCreateModel {
    private String Name;
    private Sex Gender;
    private double Salary;
}
