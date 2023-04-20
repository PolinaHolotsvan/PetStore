package com.example.petstore.Models.DirectorModels;

import java.util.UUID;

public class DirectorUpdateModel extends DirectorCreateModel{
    private UUID Id;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }
}
