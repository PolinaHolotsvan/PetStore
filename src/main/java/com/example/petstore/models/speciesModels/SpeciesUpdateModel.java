package com.example.petstore.models.speciesModels;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class SpeciesUpdateModel extends SpeciesCreateModel{
    private UUID id;
}
