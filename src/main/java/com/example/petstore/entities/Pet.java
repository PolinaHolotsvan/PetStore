package com.example.petstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Pet {
    @Id
    private UUID id;
    private LocalDate dateOfBirth;
    private double price;
    @ManyToOne
    private Species species;

    @ManyToOne
    private PetStore petStore;
}
