package com.example.petstore.Models.PetStoreModels;

import com.example.petstore.Entities.Director;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class PetStoreCreateModel extends PetStoreBasicModel{
    private UUID DirectorId;
}
