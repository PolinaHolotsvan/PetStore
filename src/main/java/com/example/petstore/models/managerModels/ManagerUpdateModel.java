package com.example.petstore.models.managerModels;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ManagerUpdateModel extends ManagerBasicModel{
    private UUID id;
}
