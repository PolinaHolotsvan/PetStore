package com.example.petstore.models.sellerModels;

import com.example.petstore.entities.PetStore;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SellerViewModel extends SellerUpdateModel {
    private Set<String> petStoreNames = new HashSet<>();

    public void convertPets(List<PetStore> petStores) {
        for (PetStore petStore : petStores) {
            petStoreNames.add(petStore.getName());
        }
    }
}
