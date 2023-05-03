package com.example.petstore.models.goodsModels;

import com.example.petstore.entities.PetStore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsViewModel extends GoodsUpdateModel{
    private String petStoreName;

    public void convertPetStore(PetStore petStore) {
        petStoreName = petStore.getName();
    }
}
