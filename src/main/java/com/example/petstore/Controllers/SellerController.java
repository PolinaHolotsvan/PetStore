package com.example.petstore.Controllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;

public class SellerController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public SellerController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
