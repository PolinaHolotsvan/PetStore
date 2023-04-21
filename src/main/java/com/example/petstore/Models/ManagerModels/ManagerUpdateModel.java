package com.example.petstore.Models.ManagerModels;

import lombok.Data;

import java.util.UUID;
@Data
public class ManagerUpdateModel extends ManagerBasicModel{
    private UUID Id;
}
