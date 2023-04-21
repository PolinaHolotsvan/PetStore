package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Seller {
    @Id
    private UUID Id;
    private String Name;
    private Sex Gender;
   private double Salary;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {
        CascadeType.PERSIST,
                CascadeType.MERGE
    }, mappedBy = "Sellers")
    private Set<PetStore> PetStores=new HashSet<>();
}
