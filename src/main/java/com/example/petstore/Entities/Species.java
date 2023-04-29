package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Species {
    @Id
    private UUID Id;
    private String Name;
    private String Description;

    @OneToMany(mappedBy = "Species", cascade = {
            CascadeType.ALL
    }, orphanRemoval = true)
    private List<Pet> Pets=new ArrayList<>();

    public void addPet(Pet pet) {
        Pets.add(pet);
        pet.setSpecies(this);
    }
    public void removePet(Pet pet) {
        Pets.remove(pet);
        pet.setSpecies(null);
    }
}
