package com.example.petstore.models.petModels;

import com.example.petstore.entities.PetStore;
import com.example.petstore.entities.Species;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetViewModel extends PetUpdateModel{
    private String speciesName;
    private String petStoreName;

    public void convertSpecies(Species species){
        speciesName =species.getName();
    }

    public void convertPetStore(PetStore petStore) {
        petStoreName = petStore.getName();
    }
}
