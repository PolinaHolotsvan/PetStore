package com.example.petstore.models.goodsModels;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class GoodsUpdateModel extends GoodsBasicModel{
    private UUID id;
}
