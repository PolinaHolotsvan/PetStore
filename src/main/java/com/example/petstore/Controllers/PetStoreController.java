package com.example.petstore.Controllers;

import com.example.petstore.Entities.*;
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
    private final ModelMapper modelMapper;
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    public PetStoreController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public void create(@RequestBody PetStoreCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        PetStore petStore = modelMapper.map(model, PetStore.class);
        petStore.setId(UUID.randomUUID());

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

            model.convertPets(petStore.getPets());
            model.convertGoods(petStore.getGoods());
            model.convertManagers(petStore.getManagers());
            model.convertSellers(petStore.getSellers());
            model.convertDirector(petStore.getDirector());

            models.add(model);
        }

        return models;
    }

    @GetMapping("/getById")
    public PetStoreViewModel getById(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        PetStore petStore = em.find(PetStore.class, id);
        PetStoreViewModel model = modelMapper.map(petStore, PetStoreViewModel.class);

        model.convertPets(petStore.getPets());
        model.convertGoods(petStore.getGoods());
        model.convertManagers(petStore.getManagers());
        model.convertSellers(petStore.getSellers());
        model.convertDirector(petStore.getDirector());

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

        Director director = petStore.getDirector();
        List<Seller> sellers = petStore.getSellers();
        List<Pet> pets = petStore.getPets();
        List<Goods> goods = petStore.getGoods();
        List<Manager> managers = petStore.getManagers();

        petStore = modelMapper.map(model, PetStore.class);

        petStore.setDirector(director);
        petStore.setSellers(sellers);
        petStore.setManagers(managers);
        petStore.setPets(pets);
        petStore.setGoods(goods);

        em.merge(petStore);
        em.getTransaction().commit();
    }
}
