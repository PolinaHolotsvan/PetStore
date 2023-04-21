package com.example.petstore.Models.GoodsModels;

import lombok.Data;

import java.util.UUID;
@Data
public class GoodsUpdateModel extends GoodsBasicModel{
    private UUID Id;
}
