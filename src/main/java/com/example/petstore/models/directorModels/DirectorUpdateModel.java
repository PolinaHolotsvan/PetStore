package com.example.petstore.models.directorModels;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class DirectorUpdateModel extends DirectorCreateModel{
    private UUID id;
}
