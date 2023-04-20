package com.example.petstore.Models.DirectorModels;

import com.example.petstore.Entities.PetStore;

public class DirectorViewModel extends DirectorUpdateModel{
    private PetStore PetStore;

    public com.example.petstore.Entities.PetStore getPetStore() {
        return PetStore;
    }

    public void setPetStore(com.example.petstore.Entities.PetStore petStore) {
        PetStore = petStore;
    }
}
