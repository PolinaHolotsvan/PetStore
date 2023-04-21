package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Goods {
    @Id
    private UUID Id;
    private String Name;
    private String Category;
    private double Price;

    @ManyToOne
    private PetStore PetStore;
}
