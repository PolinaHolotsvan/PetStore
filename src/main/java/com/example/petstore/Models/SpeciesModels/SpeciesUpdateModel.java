package com.example.petstore.Models.SpeciesModels;

import lombok.Data;

import java.util.UUID;
@Data
public class SpeciesUpdateModel extends SpeciesCreateModel{
    private UUID Id;
}
