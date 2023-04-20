package com.example.petstore.Models.GoodsModels;

import com.example.petstore.Entities.PetStore;
import lombok.Data;

@Data
public class GoodsViewModel extends GoodsUpdateModel{
    private PetStore PetStore;
}
