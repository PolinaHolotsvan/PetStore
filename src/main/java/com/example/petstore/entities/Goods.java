package com.example.petstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Goods {
    @Id
    private UUID id;
    private String name;
    private String category;
    private double price;

    @ManyToOne
    private PetStore petStore;
}
