package com.example.petstore.Models.PetStoreModels;

import lombok.Data;

import java.util.UUID;
@Data
public class PetStoreUpdateModel extends PetStoreBasicModel{
    private UUID Id;
}
