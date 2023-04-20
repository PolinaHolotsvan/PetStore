package com.example.petstore.Models.SellerModels;

import lombok.Data;

import java.util.UUID;
@Data
public class SellerUpdateModel extends SellerCreateModel{
    private UUID Id;
}
