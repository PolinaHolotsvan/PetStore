package com.example.petstore.Models.PetModels;

import com.example.petstore.Entities.PetStore;
import com.example.petstore.Entities.Species;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
@Data
public class PetCreateModel {
    private LocalDate DateOfBirth;
    private double Price;
    private UUID SpeciesId;
    private UUID PetStoreId;
}
