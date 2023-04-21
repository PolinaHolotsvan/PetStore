package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

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
    private List<PetStore> PetStores=new ArrayList<>();
}
