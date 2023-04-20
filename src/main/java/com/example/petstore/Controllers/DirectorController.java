package com.example.petstore.Controllers;

import com.example.petstore.Entities.Director;
import com.example.petstore.Entities.PetStore;
import com.example.petstore.Models.DirectorModels.DirectorCreateModel;
import com.example.petstore.Models.DirectorModels.DirectorUpdateModel;
import com.example.petstore.Models.DirectorModels.DirectorViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/director")
public class DirectorController {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public DirectorController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Director director = em.find(Director.class, id);

        em.remove(director);
        em.getTransaction().commit();
    }

    @PutMapping("/update")
    public void update(@RequestBody DirectorUpdateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Director director = em.find(Director.class, model.getId());
        director = modelMapper.map(model, Director.class);

        em.merge(director);
        em.getTransaction().commit();
    }

    @PostMapping("/create")
    public void create(@RequestBody DirectorCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Director director = modelMapper.map(model, Director.class);

        em.persist(director);
        em.getTransaction().commit();
    }

    @GetMapping("/getAll")
    public List<DirectorViewModel> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Director> directors = em.createQuery("from Director").getResultList();
        List<DirectorViewModel> models = new ArrayList<>();

        for (Director director : directors) {
            DirectorViewModel model = modelMapper.map(director, DirectorViewModel.class);
            models.add(model);
        }

        return models;
    }

    @GetMapping("/getById")
    public DirectorViewModel getById(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Director director = em.find(Director.class, id);
        DirectorViewModel model = modelMapper.map(director, DirectorViewModel.class);

        return model;
    }
}
