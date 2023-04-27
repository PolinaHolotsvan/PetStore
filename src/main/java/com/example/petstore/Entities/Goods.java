package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Goods {
    @Id
    private UUID Id;
    private String Name;
    private String Category;
    private double Price;

    @ManyToOne
    private PetStore PetStore;
}
