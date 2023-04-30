package com.example.petstore.Models.SpeciesModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SpeciesCreateModel {
    @Size(min = 5, max = 250, message = "Invalid name length")
    @NotEmpty
    private String Name;
    @Size(min = 5, max = 250, message = "Invalid name length")
    @NotEmpty
    private String Description;
}
