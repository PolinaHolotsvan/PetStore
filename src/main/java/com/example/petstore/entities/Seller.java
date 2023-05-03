package com.example.petstore.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class Seller {
    @Id
    private UUID id;
    private String name;
    private Sex gender;
    private double salary;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {
                CascadeType.ALL
    }, mappedBy = "sellers")
    private List<PetStore> petStores =new ArrayList<>();
}
