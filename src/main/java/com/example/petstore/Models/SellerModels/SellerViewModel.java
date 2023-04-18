package com.example.petstore.Models.SellerModels;

import com.example.petstore.Entities.PetStore;

import java.util.HashSet;
import java.util.Set;

public class SellerViewModel extends SellerUpdateModel{
    public Set<PetStore> PetStores=new HashSet<>();
}
