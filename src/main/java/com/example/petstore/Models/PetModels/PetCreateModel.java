package com.example.petstore.Models.PetModels;

import com.example.petstore.Entities.PetStore;
import com.example.petstore.Entities.Species;

import java.time.LocalDate;
import java.util.UUID;

public class PetCreateModel {
    public LocalDate DateOfBirth;
    public double Price;
    public UUID SpeciesId;
    public UUID PetStoreId;
}
