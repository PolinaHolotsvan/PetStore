package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Director {
    @Id
    private UUID Id;
    private String Name;
    private Sex Gender;
    private double Salary;

    @OneToOne(mappedBy = "Director", cascade = {
            CascadeType.MERGE,
            CascadeType.REMOVE
    },
            fetch = FetchType.LAZY, optional = false)
    private PetStore PetStore;
}
