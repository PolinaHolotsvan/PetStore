package com.example.petstore.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String Name;
    private Sex Gender;
    private double Salary;

    @OneToOne(mappedBy = "Director")
    private PetStore PetStore;

    public Director(UUID id, String name, Sex gender, double salary, com.example.petstore.Entities.PetStore petStore) {
        Id = id;
        Name = name;
        Gender = gender;
        Salary = salary;
        PetStore = petStore;
    }

    public Director() {
    }

    public UUID getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Sex getGender() {
        return Gender;
    }

    public void setGender(Sex gender) {
        Gender = gender;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public com.example.petstore.Entities.PetStore getPetStore() {
        return PetStore;
    }

    public void setPetStore(com.example.petstore.Entities.PetStore petStore) {
        PetStore = petStore;
    }
}
