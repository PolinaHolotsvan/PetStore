package com.example.petstore.Models.DirectorModels;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class DirectorUpdateModel extends DirectorCreateModel{
    private UUID Id;
}
