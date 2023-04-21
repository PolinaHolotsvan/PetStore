package com.example.petstore.Models.PetStoreModels;

import com.example.petstore.Entities.Director;
import lombok.Data;

import java.util.UUID;
@Data
public class PetStoreCreateModel extends PetStoreBasicModel{
    private UUID DirectorId;
}
