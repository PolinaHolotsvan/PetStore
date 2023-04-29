package com.example.petstore.Models.DirectorModels;

import com.example.petstore.Entities.PetStore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DirectorViewModel extends DirectorUpdateModel {
    private String PetStoreId;

    public void convertPetStore(PetStore petStore) {
        PetStoreId = petStore.getName();
    }
}
