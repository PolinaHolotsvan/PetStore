package com.example.petstore.Models.DirectorModels;

import lombok.Data;

import java.util.UUID;
@Data
public class DirectorUpdateModel extends DirectorCreateModel{
    private UUID Id;
}
