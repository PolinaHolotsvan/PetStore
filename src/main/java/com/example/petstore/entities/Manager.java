package com.example.petstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Manager {
    @Id
    private UUID id;
    private String name;
    private Sex gender;
    private double salary;

    @ManyToOne
    private PetStore petStore;
}
