package com.example.petstore.Models.GoodsModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GoodsCreateModel extends GoodsBasicModel{

    private UUID PetStoreId;
}
