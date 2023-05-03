package com.example.petstore.models.directorModels;

import com.example.petstore.entities.PetStore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectorViewModel extends DirectorUpdateModel {
    private String petStoreName;

    public void convertPetStore(PetStore petStore) {
        petStoreName = petStore.getName();
    }
}
