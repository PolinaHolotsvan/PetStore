package com.example.petstore.models.petModels;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetBasicModel {
    private LocalDate dateOfBirth;
    private double price;
}
