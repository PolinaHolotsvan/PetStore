package com.example.petstore.Models.ManagerModels;

import com.example.petstore.Entities.PetStore;
import com.example.petstore.Entities.Sex;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ManagerCreateModel extends ManagerBasicModel{
    private UUID PetStoreId;
}
