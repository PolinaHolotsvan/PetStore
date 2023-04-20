package com.example.petstore.Models.SpeciesModels;

import com.example.petstore.Entities.Pet;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class SpeciesViewModel extends SpeciesUpdateModel{
    private List<Pet> Pets=new ArrayList<>();
}
