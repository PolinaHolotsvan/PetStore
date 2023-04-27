package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
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
