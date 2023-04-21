package com.example.petstore.Models.DirectorModels;

import com.example.petstore.Entities.Pet;
import com.example.petstore.Entities.PetStore;
import lombok.Data;

import java.util.UUID;

@Data
public class DirectorViewModel extends DirectorUpdateModel{
    private UUID PetStoreId;

    public void convertPetStore(PetStore petStore){
        PetStoreId=petStore.getId();
    }
}
