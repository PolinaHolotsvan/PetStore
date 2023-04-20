package com.example.petstore.Models.PetModels;

import lombok.Data;

import java.util.UUID;
@Data
public class PetUpdateModel extends PetCreateModel{
    private UUID Id;
}
