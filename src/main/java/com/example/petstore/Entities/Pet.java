package com.example.petstore.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Id;
    public String Species;
    public String Breed;
    public double Age;
    public double Price;

    @ManyToOne
    public PetStore PetStore;

    public Pet(UUID id, String species, String breed, double age, double price, com.example.petstore.Entities.PetStore petStore) {
        Id = id;
        Species = species;
        Breed = breed;
        Age = age;
        Price = price;
        PetStore = petStore;
    }

    public Pet() {
    }
}
