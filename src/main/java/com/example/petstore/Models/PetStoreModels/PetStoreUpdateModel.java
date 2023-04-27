package com.example.petstore.Models.PetStoreModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class PetStoreUpdateModel extends PetStoreBasicModel{
    private UUID Id;
}
