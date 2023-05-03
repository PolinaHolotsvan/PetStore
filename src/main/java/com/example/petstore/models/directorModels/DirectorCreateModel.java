package com.example.petstore.models.directorModels;

import com.example.petstore.entities.Sex;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
public class DirectorCreateModel {
    @Size(min = 5, max = 250, message = "Invalid name length")
    @NotEmpty
    private String name;
    private Sex gender;
    @Min(value = 6700, message = "The minimum wage in Ukraine is 6,700 hryvnias")
    private double salary;
}
