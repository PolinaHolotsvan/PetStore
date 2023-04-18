package com.example.petstore.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Id;
    public String Name;
    public String Description;

    @OneToMany(mappedBy = "Species", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Pet> Pets=new ArrayList<>();

    public Species(UUID id, String name, String description, List<Pet> pets) {
        Id = id;
        Name = name;
        Description = description;
        Pets = pets;
    }

    public Species() {
    }

    public void addPet(Pet pet) {
        Pets.add(pet);
        pet.Species=this;
    }
    public void removePet(Pet pet) {
        Pets.remove(pet);
        pet.Species=null;
    }
}
