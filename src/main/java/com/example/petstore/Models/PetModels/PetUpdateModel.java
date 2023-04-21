package com.example.petstore.Models.PetModels;

import lombok.Data;

import java.util.UUID;
@Data
public class PetUpdateModel extends PetBasicModel{
    private UUID Id;
}
