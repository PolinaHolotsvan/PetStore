package com.example.petstore.Models.PetModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class PetCreateModel extends PetBasicModel{
    private UUID SpeciesId;
    private UUID PetStoreId;
}
