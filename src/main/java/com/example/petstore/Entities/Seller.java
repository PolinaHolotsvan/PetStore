package com.example.petstore.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
public class Seller {
    @Id
    private UUID Id;
    private String Name;
    private Sex Gender;
    private double Salary;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {
                CascadeType.ALL
    }, mappedBy = "Sellers")
    private List<PetStore> PetStores=new ArrayList<>();
}
