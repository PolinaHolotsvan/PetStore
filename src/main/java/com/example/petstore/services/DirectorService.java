package com.example.petstore.services;

import com.example.petstore.entities.Director;
import com.example.petstore.entities.PetStore;
import com.example.petstore.models.directorModels.DirectorCreateModel;
import com.example.petstore.models.directorModels.DirectorUpdateModel;
import com.example.petstore.models.directorModels.DirectorViewModel;
import com.example.petstore.repositories.IDirectorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DirectorService{

    private final IDirectorRepository repository;

    private final ModelMapper modelMapper;

    public List<DirectorViewModel> getAll(){

        List<Director> directors = repository.findAll();
        List<DirectorViewModel> models = new ArrayList<>();

        for (Director director : directors) {
            DirectorViewModel model = modelMapper.map(director, DirectorViewModel.class);
            if (director.getPetStore() != null)
                model.convertPetStore(director.getPetStore());
            models.add(model);
        }
        return models;
    }

    public List<DirectorViewModel> getAllFree(){

        List<DirectorViewModel> models = this.getAll().stream()
                .filter(t -> t.getPetStoreName() == null)
                .map(t -> {
                    DirectorViewModel model;
                    model=modelMapper.map(t, DirectorViewModel.class);
                    model.setPetStoreName(t.getPetStoreName());
                    return model;
                })
                .toList();
        return models;
    }

    public void create(DirectorCreateModel model) {
        Director director = modelMapper.map(model, Director.class);
        director.setId(UUID.randomUUID());
        repository.save(director);
    }

    public void update(DirectorUpdateModel model) {

        Director director = repository.findById(model.getId()).orElseThrow();
        PetStore petStore = director.getPetStore();
        director = modelMapper.map(model, Director.class);
        director.setPetStore(petStore);

        repository.save(director);
    }

    public void delete(UUID id) {
        Director director = repository.findById(id).orElseThrow();
        repository.delete(director);
    }
    public String isUnique(String name){
        List<DirectorViewModel> list= this.getAll();
        for(DirectorViewModel directorViewModel: list){
            if(Objects.equals(directorViewModel.getName(), name)) return "You can not use the same name twice";
        }
        return "";
    }

    public String isUnique2(String name, UUID id){
        List<DirectorViewModel> list= this.getAll();
        for(DirectorViewModel directorViewModel: list){
            if((directorViewModel.getId()!=id) && directorViewModel.getName()==name) return "You can not use the same name twice";
        }
        return "";
    }


}
