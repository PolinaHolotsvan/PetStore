package com.example.petstore.Models.ManagerModels;

import com.example.petstore.Entities.PetStore;
import lombok.Data;

import java.util.UUID;

@Data
public class ManagerViewModel extends ManagerUpdateModel{
    private UUID PetStoreId;
    public void convertPetStore(PetStore petStore){
        PetStoreId=petStore.getId();
    }
}
