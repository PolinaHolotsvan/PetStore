package com.example.petstore.Models.PetModels;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PetBasicModel {
    private LocalDate DateOfBirth;
    private double Price;
}
