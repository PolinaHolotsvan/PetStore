package com.example.petstore.Controllers;

import com.example.petstore.Entities.Species;
import com.example.petstore.Models.SpeciesModels.SpeciesCreateModel;
import com.example.petstore.Models.SpeciesModels.SpeciesUpdateModel;
import com.example.petstore.Models.SpeciesModels.SpeciesViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/species")
public class SpeciesController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public SpeciesController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Species species = em.find(Species.class, id);

        em.remove(species);
        em.getTransaction().commit();
    }

    @PutMapping("/update")
    public void update(@RequestBody SpeciesUpdateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Species species = em.find(Species.class, model.getId());
        species = modelMapper.map(model, Species.class);

        em.merge(species);
        em.getTransaction().commit();
    }

    @PostMapping("/create")
    public void create(@RequestBody SpeciesCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Species species = modelMapper.map(model, Species.class);

        em.persist(species);
        em.getTransaction().commit();
    }

    @GetMapping("/getAll")
    public List<SpeciesViewModel> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Species> species = em.createQuery("from Species").getResultList();
        List<SpeciesViewModel> models = new ArrayList<>();

        for (Species species1 : species) {
            SpeciesViewModel model = modelMapper.map(species1, SpeciesViewModel.class);
            models.add(model);
        }

        return models;
    }

    @GetMapping("/getById")
    public SpeciesViewModel getById(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Species species = em.find(Species.class, id);
        SpeciesViewModel model = modelMapper.map(species, SpeciesViewModel.class);

        return model;
    }
}
