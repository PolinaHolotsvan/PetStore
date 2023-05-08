package com.example.petstore.models.directorModels;

import com.example.petstore.entities.Sex;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class DirectorUpdateModel extends DirectorCreateModel{
    private UUID id;

    public DirectorUpdateModel(@NotEmpty(message = "This field can not be empty") @Pattern(regexp = "[a-zA-Z0-9]+\s[a-zA-Z0-9]+", message = "Name and surname must be included") String name,
                               Sex gender, @Min(value = 6700, message = "The minimum wage in Ukraine is 6,700 hryvnias") double salary, UUID id) {
        super(name, gender, salary);
        this.id = id;
    }
}
