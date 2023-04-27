package com.example.petstore.Models.PetModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class PetUpdateModel extends PetBasicModel{
    private UUID Id;
}
