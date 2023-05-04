package com.example.petstore.models.sellerModels;

import com.example.petstore.entities.Sex;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerCreateModel {
    @NotEmpty(message = "This field can not be empty")
    @Pattern(regexp = "[a-zA-Z0-9]+\s[a-zA-Z0-9]+",
            message = "Name and surname must be included")
    private String name;
    private Sex gender;
    @Min(value = 6700, message = "The minimum wage in Ukraine is 6,700 hryvnias")
    private double salary;
}
