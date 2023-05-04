package com.example.petstore.models.petStoreModels;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class PetStoreBasicModel {
    @NotEmpty(message = "This field can not be empty")
    private String name;
    @Range(min=1, max = 10, message = "The rating is in range (1;10)")
    private double rating;
}
