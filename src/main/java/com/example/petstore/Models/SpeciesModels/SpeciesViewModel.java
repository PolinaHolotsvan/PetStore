package com.example.petstore.Models.SpeciesModels;

import com.example.petstore.Entities.Pet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SpeciesViewModel extends SpeciesUpdateModel{
    private List<UUID> PetsId=new ArrayList<>();

    public void convertPets(List<Pet> pets) {
        for (Pet pet : pets) {
            PetsId.add(pet.getId());
        }
    }
}
