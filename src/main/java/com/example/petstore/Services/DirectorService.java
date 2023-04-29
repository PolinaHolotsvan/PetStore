package com.example.petstore.Services;

import com.example.petstore.Entities.Director;
import com.example.petstore.Entities.PetStore;
import com.example.petstore.Models.DirectorModels.DirectorCreateModel;
import com.example.petstore.Models.DirectorModels.DirectorUpdateModel;
import com.example.petstore.Models.DirectorModels.DirectorViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DirectorService {
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    private final ModelMapper modelMapper;

    public DirectorService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<DirectorViewModel> getAll(){
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Director> directors = em.createQuery("from Director").getResultList();
        List<DirectorViewModel> models = new ArrayList<>();

        for (Director director : directors) {
            DirectorViewModel model = modelMapper.map(director, DirectorViewModel.class);
            if (director.getPetStore() != null)
                model.convertPetStore(director.getPetStore());
            models.add(model);
        }
        em.close();
        return models;
    }

    public List<DirectorViewModel> getAllFree(){
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Director> directors = em.createQuery("from Director").getResultList();
        List<DirectorViewModel> models = new ArrayList<>();

        for (Director director : directors) {
            DirectorViewModel model = modelMapper.map(director, DirectorViewModel.class);
            if (director.getPetStore() == null)
                models.add(model);
        }
        em.close();
        return models;
    }

    public DirectorViewModel getById(UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Director director = em.find(Director.class, id);
        DirectorViewModel model = modelMapper.map(director, DirectorViewModel.class);
        if (director.getPetStore() != null)
            model.convertPetStore(director.getPetStore());

        em.close();
        return model;
    }

    public void create(DirectorCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Director director = modelMapper.map(model, Director.class);
        director.setId(UUID.randomUUID());

        em.persist(director);
        em.getTransaction().commit();
        em.close();
    }

    public void update(DirectorUpdateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Director director = em.find(Director.class, model.getId());
        PetStore petStore = director.getPetStore();
        director = modelMapper.map(model, Director.class);
        director.setPetStore(petStore);

        em.merge(director);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Director director = em.find(Director.class, id);

        em.remove(director);
        em.getTransaction().commit();
        em.close();
    }
}
