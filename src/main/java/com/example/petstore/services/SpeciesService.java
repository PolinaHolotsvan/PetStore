package com.example.petstore.services;

import com.example.petstore.entities.Pet;
import com.example.petstore.entities.Species;
import com.example.petstore.models.sellerModels.SellerViewModel;
import com.example.petstore.models.speciesModels.SpeciesCreateModel;
import com.example.petstore.models.speciesModels.SpeciesUpdateModel;
import com.example.petstore.models.speciesModels.SpeciesViewModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class SpeciesService {
    private final ModelMapper modelMapper;
    @PersistenceUnit(name = "Entities")
    private EntityManagerFactory entityManagerFactory;

    public SpeciesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<SpeciesViewModel> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();

        List<Species> species = em.createQuery("from Species").getResultList();
        List<SpeciesViewModel> models = new ArrayList<>();

        for (Species species1 : species) {
            SpeciesViewModel model = modelMapper.map(species1, SpeciesViewModel.class);
            model.convertPets(species1.getPets());
            models.add(model);
        }
        em.close();
        return models;
    }

    public void update(SpeciesUpdateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Species species = em.find(Species.class, model.getId());
        List<Pet> pets = species.getPets();

        species = modelMapper.map(model, Species.class);
        species.setPets(pets);

        em.merge(species);
        em.getTransaction().commit();
        em.close();
    }

    public void create(SpeciesCreateModel model) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Species species = modelMapper.map(model, Species.class);
        species.setId(UUID.randomUUID());

        em.persist(species);
        em.getTransaction().commit();
        em.close();
    }

    public SpeciesViewModel getById(UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Species species = em.find(Species.class, id);
        SpeciesViewModel model = modelMapper.map(species, SpeciesViewModel.class);
        model.convertPets(species.getPets());
        em.close();

        return model;
    }

    public void delete(@RequestParam UUID id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Species species = em.find(Species.class, id);

        em.remove(species);
        em.getTransaction().commit();
    }

    public String isUnique(String name){
        List<SpeciesViewModel> list= this.getAll();
        for(SpeciesViewModel speciesViewModel: list){
            if(Objects.equals(speciesViewModel.getName(), name)) return "You can not use the same name twice";
        }
        return "";
    }

    public String isUnique2(String name, UUID id){
        List<SpeciesViewModel> list= this.getAll();
        for(SpeciesViewModel speciesViewModel: list){
            if((speciesViewModel.getId()!=id) && speciesViewModel.getName()==name) return "You can not use the same name twice";
        }
        return "";
    }
}
