package com.example.petstore.Models.PetModels;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
@Data
public class PetCreateModel extends PetBasicModel{
    private UUID SpeciesId;
    private UUID PetStoreId;
}
