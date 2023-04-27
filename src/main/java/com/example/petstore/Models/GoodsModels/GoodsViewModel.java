package com.example.petstore.Models.GoodsModels;

import com.example.petstore.Entities.PetStore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GoodsViewModel extends GoodsUpdateModel{
    private UUID PetStoreId;

    public void convertPetStore(PetStore petStore) {
        PetStoreId = petStore.getId();
    }
}
