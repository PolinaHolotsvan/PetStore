package com.example.petstore.models.speciesModels;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeciesCreateModel {
    //@Size(min = 5, max = 250, message = "Invalid name length")
    @NotEmpty(message = "This field can not be empty")
    private String name;
    @Size(max = 250, message = "Invalid length")
    @NotEmpty(message = "This field can not be empty")
    private String description;
}
