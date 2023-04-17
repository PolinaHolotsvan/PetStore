package com.example.petstore.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Id;
    public String Name;
    public String Category;
    public double Price;

    @ManyToOne
    public PetStore PetStore;

    public Goods(UUID id, String name, String category, double price, com.example.petstore.Entities.PetStore petStore) {
        Id = id;
        Name = name;
        Category = category;
        Price = price;
        PetStore = petStore;
    }

    public Goods() {
    }
}
