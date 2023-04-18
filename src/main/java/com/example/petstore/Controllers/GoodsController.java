package com.example.petstore.Controllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;

public class GoodsController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public GoodsController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
