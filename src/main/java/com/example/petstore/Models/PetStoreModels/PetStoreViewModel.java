package com.example.petstore.Models.PetStoreModels;

import com.example.petstore.Entities.*;
import lombok.Data;

import java.util.*;

@Data
public class PetStoreViewModel extends PetStoreUpdateModel{
    private UUID DirectorId;
    private Set<UUID> SellersId = new HashSet<>();
    private List<UUID> GoodsId=new ArrayList<>();
    private List<UUID> PetsId=new ArrayList<>();
    private List<UUID> ManagersId=new ArrayList<>();

    public void convertSellers(Set<Seller> sellers){
        for (Seller seller:sellers) {
            SellersId.add(seller.getId());
        }
    }

    public void convertGoods(List<Goods> goods){
        for (Goods goods1:goods) {
            GoodsId.add(goods1.getId());
        }
    }

    public void convertPets(List<Pet> pets){
        for (Pet pet:pets) {
            PetsId.add(pet.getId());
        }
    }

    public void convertManagers(List<Manager> managers){
        for (Manager manager:managers) {
            ManagersId.add(manager.getId());
        }
    }

    public void convertDirector(Director director){
        DirectorId=director.getId();
    }

}
