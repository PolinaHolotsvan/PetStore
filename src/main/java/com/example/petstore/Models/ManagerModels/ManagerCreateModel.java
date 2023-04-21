package com.example.petstore.Models.ManagerModels;

import com.example.petstore.Entities.PetStore;
import com.example.petstore.Entities.Sex;
import lombok.Data;

import java.util.UUID;
@Data
public class ManagerCreateModel extends ManagerBasicModel{
    private UUID PetStoreId;
}
