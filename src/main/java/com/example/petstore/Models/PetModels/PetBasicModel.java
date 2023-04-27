package com.example.petstore.Models.PetModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetBasicModel {
    private LocalDate DateOfBirth;
    private double Price;
}
