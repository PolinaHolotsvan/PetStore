package com.example.petstore.models.petModels;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetBasicModel {
    @NotNull(message = "This field can not be empty")
    @Past(message = "It must be a past date")
    private LocalDate dateOfBirth;
    @Min(value = 0, message = "The price can not be a negative number")
    private double price;
}
