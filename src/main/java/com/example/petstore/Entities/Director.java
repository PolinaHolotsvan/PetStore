package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Director {
    @Id
    private UUID Id;
    private String Name;
    private Sex Gender;
    private double Salary;

    @OneToOne(mappedBy = "Director", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private PetStore PetStore;
}
