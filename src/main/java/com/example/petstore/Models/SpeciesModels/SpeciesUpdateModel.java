package com.example.petstore.Models.SpeciesModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class SpeciesUpdateModel extends SpeciesCreateModel{
    private UUID Id;
}
