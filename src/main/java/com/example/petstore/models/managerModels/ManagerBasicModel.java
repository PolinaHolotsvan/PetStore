package com.example.petstore.models.managerModels;

import com.example.petstore.entities.Sex;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerBasicModel {
    private String name;
    private Sex gender;
    private double salary;
}
