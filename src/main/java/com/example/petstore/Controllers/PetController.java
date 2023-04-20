package com.example.petstore.Controllers;

import com.example.petstore.Entities.Pet;
import com.example.petstore.Entities.PetStore;
import com.example.petstore.Entities.Species;
import com.example.petstore.Models.PetModels.PetCreateModel;
import com.example.petstore.Models.PetModels.PetUpdateModel;
import com.example.petstore.Models.PetModels.PetViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pet")
public class PetController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public PetController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Pet pet = em.find(Pet.class, id);
        PetStore petStore = em.find(PetStore.class, pet.getPetStore().getId());
        petStore.removePet(pet);

        Species species = em.find(Species.class, pet.getSpecies().getName());
        species.removePet(pet);

        em.remove(pet);
        em.persist(species);
        em.persist(petStore);
        em.getTransaction().commit();
    }

    @PutMapping("/update")
    public void update(@RequestBody PetUpdateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Pet pet = em.find(Pet.class, model.getId());
        pet = modelMapper.map(model, Pet.class);

        em.merge(pet);
        em.getTransaction().commit();
    }

    @PostMapping("/create")
    public void create(@RequestBody PetCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Pet pet = modelMapper.map(model, Pet.class);

        PetStore petStore = em.find(PetStore.class, model.getPetStoreId());
        petStore.addPet(pet);

        Species species = em.find(Species.class, model.getSpeciesId());
        species.addPet(pet);

        pet.setId(UUID.randomUUID());

        em.persist(pet);
        em.merge(petStore);
        em.merge(species);
        em.getTransaction().commit();
    }

    @GetMapping("/getAll")
    public List<PetViewModel> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Pet> pets = em.createQuery("from Pet").getResultList();
        List<PetViewModel> models = new ArrayList<>();

        for (Pet pet : pets) {
            PetViewModel model = modelMapper.map(pet, PetViewModel.class);
            models.add(model);
        }

        return models;
    }

    @GetMapping("/getById")
    public PetViewModel getById(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Pet pet = em.find(Pet.class, id);
        PetViewModel model = modelMapper.map(pet, PetViewModel.class);

        return model;
    }
}
