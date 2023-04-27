package com.example.petstore.Models.SellerModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class SellerUpdateModel extends SellerCreateModel{
    private UUID Id;
}
