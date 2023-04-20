package com.example.petstore.Models.PetStoreModels;

import com.example.petstore.Entities.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class PetStoreViewModel extends PetStoreUpdateModel{
    private Set<Seller> Sellers = new HashSet<>();
    private List<Goods> Goods=new ArrayList<>();
    private List<Pet> Pets=new ArrayList<>();
    private List<Manager> Managers=new ArrayList<>();

}
