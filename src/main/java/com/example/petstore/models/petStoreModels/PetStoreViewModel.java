package com.example.petstore.models.petStoreModels;

import com.example.petstore.entities.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class PetStoreViewModel extends PetStoreUpdateModel {
    private String directorName;
    private Set<String> sellerNames = new HashSet<>();
    private Set<UUID> sellerIds = new HashSet<>();
    private List<String> goodNames = new ArrayList<>();
    private List<UUID> petIds = new ArrayList<>();
    private List<String> managerNames = new ArrayList<>();

    public void convertSellers(List<Seller> sellers) {
        for (Seller seller : sellers) {
            sellerNames.add(seller.getName());
            sellerIds.add(seller.getId());
        }
    }

    public void convertGoods(List<Goods> goods) {
        for (Goods goods1 : goods) {
            goodNames.add(goods1.getName());
        }
    }

    public void convertPets(List<Pet> pets) {
        for (Pet pet : pets) {
            petIds.add(pet.getId());
        }
    }

    public void convertManagers(List<Manager> managers) {
        for (Manager manager : managers) {
            managerNames.add(manager.getName());
        }
    }

    public void convertDirector(Director director) {
        directorName = director.getName();
    }

}
