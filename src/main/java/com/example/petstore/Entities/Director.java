package com.example.petstore.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Id;
    public String Name;
    public Sex Gender;
    public double Salary;

    @OneToOne(mappedBy = "Director")
    public PetStore PetStore;

    public Director(UUID id, String name, Sex gender, double salary, com.example.petstore.Entities.PetStore petStore) {
        Id = id;
        Name = name;
        Gender = gender;
        Salary = salary;
        PetStore = petStore;
    }

    public Director() {
    }
}
