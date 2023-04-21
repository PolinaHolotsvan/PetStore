package com.example.petstore.Models.GoodsModels;

import com.example.petstore.Entities.PetStore;
import lombok.Data;

import java.util.UUID;

@Data
public class GoodsCreateModel extends GoodsBasicModel{

    private UUID PetStoreId;
}
