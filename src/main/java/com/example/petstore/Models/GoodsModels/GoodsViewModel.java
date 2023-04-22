package com.example.petstore.Models.GoodsModels;

import com.example.petstore.Entities.PetStore;
import lombok.Data;

import java.util.UUID;

@Data
public class GoodsViewModel extends GoodsUpdateModel{
    private UUID PetStoreId;

    public void convertPetStore(PetStore petStore) {
        PetStoreId = petStore.getId();
    }
}
