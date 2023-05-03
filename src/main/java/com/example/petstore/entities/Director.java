package com.example.petstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Director {
    @Id
    private UUID id;

    private String name;
    private Sex gender;

    private double salary;

    @OneToOne(mappedBy = "director", cascade = {
            CascadeType.MERGE,
            CascadeType.REMOVE
    },
            fetch = FetchType.LAZY, optional = false)
    private PetStore petStore;
}
