package com.example.petstore.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID Id;
    public String Name;
    public String Gender;
    public double Salary;

    @ManyToOne
    public PetStore PetStore;

    public Manager(UUID id, String name, String gender, double salary, com.example.petstore.Entities.PetStore petStore) {
        Id = id;
        Name = name;
        Gender = gender;
        Salary = salary;
        PetStore = petStore;
    }

    public Manager() {
    }
}
