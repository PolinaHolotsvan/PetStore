package com.example.petstore.models.petModels;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class PetCreateModel extends PetBasicModel{
    private UUID speciesId;
    private UUID petStoreId;
}
