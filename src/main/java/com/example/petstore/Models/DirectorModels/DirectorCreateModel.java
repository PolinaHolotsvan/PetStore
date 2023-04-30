package com.example.petstore.Models.DirectorModels;

import com.example.petstore.Entities.Sex;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class DirectorCreateModel {
    @Size(min = 5, max = 250, message = "Invalid name length")
    @NotEmpty
    private String Name;
    private Sex Gender;
    @NotEmpty
    @Min(value = 6700, message = "The minimum wage in Ukraine is 6,700 hryvnias")
    private double Salary;
}
