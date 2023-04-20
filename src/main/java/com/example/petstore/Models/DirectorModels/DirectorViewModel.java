package com.example.petstore.Models.DirectorModels;

import com.example.petstore.Entities.PetStore;
import lombok.Data;

@Data
public class DirectorViewModel extends DirectorUpdateModel{
    private PetStore PetStore;
}
