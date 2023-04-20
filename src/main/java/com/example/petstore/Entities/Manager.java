package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String Name;
    private Sex Gender;
    private double Salary;

    @ManyToOne
    private PetStore PetStore;
}
