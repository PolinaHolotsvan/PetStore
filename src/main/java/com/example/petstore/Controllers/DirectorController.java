package com.example.petstore.Controllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;

public class DirectorController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public DirectorController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
