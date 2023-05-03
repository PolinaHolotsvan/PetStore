package com.example.petstore.models.petStoreModels;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class PetStoreCreateModel extends PetStoreBasicModel{
    private UUID directorId;
}
