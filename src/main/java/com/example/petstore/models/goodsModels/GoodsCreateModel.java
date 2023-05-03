package com.example.petstore.models.goodsModels;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GoodsCreateModel extends GoodsBasicModel{

    private UUID petStoreId;
}
