package com.example.petstore.Controllers;

import com.example.petstore.Entities.Director;
import com.example.petstore.Entities.PetStore;
import com.example.petstore.Models.PetStoreModels.PetStoreCreateModel;
import com.example.petstore.Models.PetStoreModels.PetStoreUpdateModel;
import com.example.petstore.Models.PetStoreModels.PetStoreViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/petStore")
public class PetStoreController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public PetStoreController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public void create(@RequestBody PetStoreCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        PetStore petStore = modelMapper.map(model, PetStore.class);
        Director director = em.find(Director.class, model.getDirectorId());
        petStore.setDirector(director);

        em.merge(director);
        em.getTransaction().commit();
    }

    @GetMapping("/getAll")
    public List<PetStoreViewModel> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<PetStore> petStores = em.createQuery("from PetStore").getResultList();
        List<PetStoreViewModel> models = new ArrayList<>();

        for (PetStore petStore : petStores) {
            PetStoreViewModel model = modelMapper.map(petStore, PetStoreViewModel.class);
            models.add(model);
        }

        return models;
    }

    @GetMapping("/getById")
    public PetStoreViewModel getById(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        PetStore petStore = em.find(PetStore.class, id);
        PetStoreViewModel model = modelMapper.map(petStore, PetStoreViewModel.class);

        return model;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        PetStore petStore = em.find(PetStore.class, id);
        Director director = em.find(Director.class, petStore.getDirector().getId());
        petStore.removeDirector(director);

        em.remove(petStore);
        em.merge(director);
        em.getTransaction().commit();
    }

    @PutMapping("/update")
    public void update(@RequestBody PetStoreUpdateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        PetStore petStore = em.find(PetStore.class, model.getId());
        petStore = modelMapper.map(model, PetStore.class);

        em.merge(petStore);
        em.getTransaction().commit();
    }
}
