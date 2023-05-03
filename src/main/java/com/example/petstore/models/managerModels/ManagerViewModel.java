package com.example.petstore.models.managerModels;

import com.example.petstore.entities.PetStore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerViewModel extends ManagerUpdateModel {
    private String petStoreName;

    public void convertPetStore(PetStore petStore) {
        petStoreName = petStore.getName();
    }
}
