package com.example.petstore.Models.PetModels;

import com.example.petstore.Entities.PetStore;
import com.example.petstore.Entities.Species;
import lombok.Data;

import java.util.UUID;

@Data
public class PetViewModel extends PetUpdateModel{
    private UUID SpeciesId;
    private UUID PetStoreId;

    public void convertSpecies(Species species){
        SpeciesId=species.getId();
    }

    public void convertPetStore(PetStore petStore) {
        PetStoreId = petStore.getId();
    }
}
