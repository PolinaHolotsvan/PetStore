package com.example.petstore.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Id;
    public String Name;
    public String Gender;
    public double Salary;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {
        CascadeType.PERSIST,
                CascadeType.MERGE
    }, mappedBy = "Sellers")
    public Set<PetStore> PetStores=new HashSet<>();

    public Seller(UUID id, String name, String gender, double salary, Set<PetStore> petStores) {
        Id = id;
        Name = name;
        Gender = gender;
        Salary = salary;
        PetStores = petStores;
    }

    public Seller() {
    }
}
