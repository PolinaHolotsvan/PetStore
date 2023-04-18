package com.example.petstore.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Id;
    public LocalDate DateOfBirth;
    public double Price;
    @ManyToOne
    public Species Species;

    @ManyToOne
    public PetStore PetStore;

    public Pet(UUID id, Species species, LocalDate dateOfBirth, double price, PetStore petStore) {
        Id = id;
        Species = species;
        DateOfBirth = dateOfBirth;
        Price = price;
        PetStore = petStore;
    }

    public Pet() {
    }
}
