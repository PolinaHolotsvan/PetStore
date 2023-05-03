package com.example.petstore.models.sellerModels;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class SellerUpdateModel extends SellerCreateModel{
    private UUID id;
}
