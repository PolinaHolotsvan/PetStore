package com.example.petstore.Models.SellerModels;

import com.example.petstore.Entities.PetStore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class SellerViewModel extends SellerUpdateModel {
    private Set<UUID> PetStoresId = new HashSet<>();

    public void convertPets(List<PetStore> petStores) {
        for (PetStore petStore : petStores) {
            PetStoresId.add(petStore.getId());
        }
    }
}
