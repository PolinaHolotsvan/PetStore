package com.example.petstore.Controllers;

import com.example.petstore.Entities.PetStore;
import com.example.petstore.Models.PetStoreModels.PetStoreCreateModel;
import com.example.petstore.Models.PetStoreModels.PetStoreViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/PetStore")
public class PetStoreController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public PetStoreController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public void add(@RequestBody PetStoreCreateModel model){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        PetStore petStore=modelMapper.map(model, PetStore.class);
        em.persist(petStore);
        em.getTransaction().commit();
    }

    @GetMapping("/getAll")
    public List<PetStoreViewModel> getAll(){
        EntityManager em=entityManagerFactory.createEntityManager();
        List<PetStore> petStores=em.createQuery("from PetStore").getResultList();
        List<PetStoreViewModel> models=new ArrayList<>();
        for (PetStore petStore:petStores) {
            PetStoreViewModel model=modelMapper.map(petStore, PetStoreViewModel.class);
            models.add(model);
        }
        return models;
    }
}
