package com.example.petstore.entities;

import jakarta.persistence.*;
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
    private UUID id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "species", cascade = {
            CascadeType.ALL
    }, orphanRemoval = true)
    private List<Pet> pets =new ArrayList<>();

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setSpecies(this);
    }
    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.setSpecies(null);
    }
}
