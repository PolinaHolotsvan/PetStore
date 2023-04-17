package com.example.petstore.Models.PetStoreModels;

import com.example.petstore.Entities.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PetStoreViewModel extends PetStoreUpdateModel{
    public Set<Seller> Sellers = new HashSet<>();
    public List<Goods> Goods=new ArrayList<>();
    public List<Pet> Pets=new ArrayList<>();
    public List<Manager> Managers=new ArrayList<>();
    public Director Director;
}
