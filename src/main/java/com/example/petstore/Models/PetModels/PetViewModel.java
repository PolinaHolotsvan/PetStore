package com.example.petstore.Models.PetModels;

import com.example.petstore.Entities.PetStore;
import com.example.petstore.Entities.Species;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PetViewModel extends PetUpdateModel{
    private String SpeciesId;
    private String  PetStoreId;

    public void convertSpecies(Species species){
        SpeciesId=species.getName();
    }

    public void convertPetStore(PetStore petStore) {
        PetStoreId = petStore.getName();
    }
}
