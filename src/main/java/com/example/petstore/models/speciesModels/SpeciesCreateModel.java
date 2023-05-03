package com.example.petstore.models.speciesModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeciesCreateModel {
    //@Size(min = 5, max = 250, message = "Invalid name length")
    //@NotEmpty
    private String name;
    //@Size(min = 5, max = 250, message = "Invalid name length")
    //@NotEmpty
    private String description;
}
