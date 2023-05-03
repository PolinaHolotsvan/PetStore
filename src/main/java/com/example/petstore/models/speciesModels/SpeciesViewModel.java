package com.example.petstore.models.speciesModels;

import com.example.petstore.entities.Pet;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SpeciesViewModel extends SpeciesUpdateModel{
    private List<UUID> petIds =new ArrayList<>();

    public void convertPets(List<Pet> pets) {
        for (Pet pet : pets) {
            petIds.add(pet.getId());
        }
    }
}
