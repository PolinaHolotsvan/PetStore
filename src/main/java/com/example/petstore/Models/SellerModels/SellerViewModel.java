package com.example.petstore.Models.SellerModels;

import com.example.petstore.Entities.PetStore;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class SellerViewModel extends SellerUpdateModel{
    private Set<PetStore> PetStores=new HashSet<>();
}
