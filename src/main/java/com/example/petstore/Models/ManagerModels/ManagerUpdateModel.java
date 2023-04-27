package com.example.petstore.Models.ManagerModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ManagerUpdateModel extends ManagerBasicModel{
    private UUID Id;
}
