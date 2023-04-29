package com.example.petstore.Models.PetStoreModels;

import com.example.petstore.Entities.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class PetStoreViewModel extends PetStoreUpdateModel {
    private String DirectorId;
    private Set<String> SellersId = new HashSet<>();
    private List<String> GoodsId = new ArrayList<>();
    private List<UUID> PetsId = new ArrayList<>();
    private List<String> ManagersId = new ArrayList<>();

    public void convertSellers(List<Seller> sellers) {
        for (Seller seller : sellers) {
            SellersId.add(seller.getName());
        }
    }

    public void convertGoods(List<Goods> goods) {
        for (Goods goods1 : goods) {
            GoodsId.add(goods1.getName());
        }
    }

    public void convertPets(List<Pet> pets) {
        for (Pet pet : pets) {
            PetsId.add(pet.getId());
        }
    }

    public void convertManagers(List<Manager> managers) {
        for (Manager manager : managers) {
            ManagersId.add(manager.getName());
        }
    }

    public void convertDirector(Director director) {
        DirectorId = director.getName();
    }

}
