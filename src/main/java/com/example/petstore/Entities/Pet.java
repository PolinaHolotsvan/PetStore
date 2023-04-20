package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Pet {
    @Id
    private UUID Id;
    private LocalDate DateOfBirth;
    private double Price;
    @ManyToOne
    private Species Species;

    @ManyToOne
    private PetStore PetStore;
}
